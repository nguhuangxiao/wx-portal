package com.web.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.web.wx.dto.MaterialDto;
import com.web.wx.dto.MaterialListDto;
import com.web.wx.req.MaterialReq;
import com.web.wx.service.WxConfigService;
import com.web.wx.service.WxMaterialService;
import com.web.wx.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/24
 */
@Service
public class WxMaterialServiceImpl implements WxMaterialService {

    @Autowired
    private WxConfigService wxConfigService;

    private final String API_MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    private final String API_MATERIAL_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    @Override
    public String addTemporary(MultipartFile file) {
        String result;
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MEDIA_UPLOAD,
            token,
            "image"
        );
        try {
            WxUtil wxUtil = new WxUtil();
            result = wxUtil.upload(file, url);
        } catch (IOException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    @Override
    public String temporaryList() {
        String result;
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MATERIAL_NEWS,
            token
        );
        return null;
    }

    @Override
    public String addPermanent(MaterialReq materialReq) {
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MATERIAL_NEWS,
            token
        );
        String params = JSONObject.toJSONString(materialReq);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(url, params, String.class);
        return result;
    }

    @Override
    public String permanentList() {
        return null;
    }

    @Override
    public String deletePermanent() {
        return null;
    }

    @Override
    public String updatePermanent() {
        return null;
    }

    @Override
    public String getTotle() {
        return null;
    }

    @Override
    public String getTypeList() {
        return null;
    }
}
