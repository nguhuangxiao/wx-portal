package com.web.wx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.wx.service.WxConfigService;
import com.web.wx.service.WxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/17
 */
@Service
public class WxMenuServiceImpl implements WxMenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxConfigService wxConfigService;

    private final static String WX_API_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    private final static String WX_API_MENU_LIST = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";


    @Override
    public String addMenu() {
        String token = wxConfigService.updateAccessToken();
        String url = WX_API_CREATE_MENU + token;

        JSONObject obj = new JSONObject();

        JSONArray arr = new JSONArray();
        //一级菜单
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "今日测歌曲");
        jsonObject.put("type", "click");
        jsonObject.put("key", "JQiI99_2OvMmv9jbJ-Go8B6gtXRPnSzvyLVz4k40jGE");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "菜单");

        //二级菜单
        JSONObject twolevelObj1 = new JSONObject();
        twolevelObj1.put("type", "view");
        twolevelObj1.put("name", "搜索");
        twolevelObj1.put("url", "http://www.soso.com/");

        JSONObject twolevelObj2 = new JSONObject();
        twolevelObj2.put("type", "miniprogram");
        twolevelObj2.put("name", "丰实跟单");
        twolevelObj2.put("url", "http://mp.weixin.qq.com");
        twolevelObj2.put("appid", "wxfbfbd2bc5978b601");
        twolevelObj2.put("pagepath", "pages/project/list");

        JSONObject twolevelObj3 = new JSONObject();
        twolevelObj3.put("type", "click");
        twolevelObj3.put("name", "联系我们");
        twolevelObj3.put("key", "CONTACT_US");

        JSONArray twolevelMenu = new JSONArray();
        twolevelMenu.add(twolevelObj1);
        twolevelMenu.add(twolevelObj2);
        twolevelMenu.add(twolevelObj3);

        jsonObject1.put("sub_button", twolevelMenu);

        arr.add(jsonObject);
        arr.add(jsonObject1);

        obj.put("button", arr);

        String resultStr = restTemplate.postForObject(url, obj, String.class);

        return resultStr;
    }

    @Override
    public String list() {
        String token = wxConfigService.updateAccessToken();
        String url = WX_API_MENU_LIST + token;
        String resultStr = restTemplate.getForObject(url, String.class);
        return resultStr;
    }
}
