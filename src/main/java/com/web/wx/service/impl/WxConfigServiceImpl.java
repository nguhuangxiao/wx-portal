package com.web.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.web.wx.mapper.WxAccessTokenMapper;
import com.web.wx.model.WxAccessToken;
import com.web.wx.property.WxProp;
import com.web.wx.service.WxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/17
 */
@Service
public class WxConfigServiceImpl implements WxConfigService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxProp wxProp;

    @Autowired(required = false)
    private WxAccessTokenMapper wxAccessTokenMapper;

    private final static String WX_API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


    @Override
    public WxAccessToken findLatest() {
        return wxAccessTokenMapper.findLatest();
    }

    /**
     * 获取token信息
     * @return
     */
    public Boolean testAccessToken(WxAccessToken list) {
        long timeStamp = list.getCreateTime().getTime() + (long)7200000;
        long currentTime = System.currentTimeMillis();
        //token时间超过2个小时，自动刷新
        System.out.println(timeStamp + "-----" + currentTime);
        if(currentTime > timeStamp){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String updateAccessToken() {
        //最新的access_token
        WxAccessToken list = wxAccessTokenMapper.findLatest();
        if(list != null) {
            //验证access_token
            if(testAccessToken(list)) {
                return list.getToken();
            }
        }
        System.out.println("test");
        String url = String.format(
            WX_API_ACCESS_TOKEN,
            wxProp.getAppId(),
            wxProp.getAppSecret()
        );
        String resultStr = restTemplate.getForObject(url, String.class);
        JSONObject rtnObj = JSONObject.parseObject(resultStr);
        WxAccessToken wxAccessToken = new WxAccessToken();

        wxAccessToken.setToken(rtnObj.getString("access_token"));
        wxAccessToken.setExpiresIn(rtnObj.getInteger("expires_in"));
        wxAccessToken.setAppId(wxProp.getAppId());
        wxAccessToken.setAppSecret(wxProp.getAppSecret());
        wxAccessToken.setCreateTime(new Date());
        wxAccessTokenMapper.insert(wxAccessToken);
        return wxAccessToken.getToken();
    }
}
