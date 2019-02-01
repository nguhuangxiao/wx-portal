package com.web.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.web.wx.req.MaterialItemReq;
import com.web.wx.req.MaterialListReq;
import com.web.wx.req.NewsReq;
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

    /** 上传临时素材 **/
    private final String API_MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    /** 下载临时素材 **/
    private final String API_MEDIA_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    /** 上传永久素材 **/
    private final String API_MATERIAL_ADD = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";

    /** 下载永久素材 **/
    private final String API_MATERIAL_GET = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";

    /** 新增图文 **/
    private final String API_ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    /** 修改图文 **/
    private final String API_UPDATE_NEWS = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";

    /** 图文列表 **/
    private final String API_NEWS_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";

    /** 素材总数 **/
    private final String API_MATERIAL_TOTLE = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";


    public static String uploadMaterial(MultipartFile file, String url) {
        String result;
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
    public String addTemporary(MultipartFile file) {
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MEDIA_UPLOAD,
            token,
            "image"
        );
        return uploadMaterial(file, url);
    }

    @Override
    public String addPermanent(MultipartFile file) {
        String result;
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MATERIAL_ADD,
            token,
            "image"
        );
        return uploadMaterial(file, url);
    }

    @Override
    public String downLoad(String type, String mediaId) {
        String token = wxConfigService.updateAccessToken();
        RestTemplate restTemplate = new RestTemplate();
        if("0".equals(type)) {
            String url = String.format(
                API_MEDIA_GET,
                token,
                mediaId
            );
            String result = restTemplate.getForObject(url, String.class);
            return result;
        }else{
            return null;
        }
    }


    @Override
    public String updateNews(Object materialItemReq, int type) {
        String token = wxConfigService.updateAccessToken();
        String api = (type == 0) ?  API_ADD_NEWS : API_UPDATE_NEWS;
        String url = String.format(
            api,
            token
        );
        String params = JSONObject.toJSONString(materialItemReq);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(url, params, String.class);
        return result;
    }


    @Override
    public String list(NewsReq newsReq) {
        String result;
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_NEWS_LIST,
            token
        );
        String params = JSONObject.toJSONString(newsReq);
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.postForObject(url, params, String.class);
        try {
            result = new String(json.getBytes("ISO-8859-1"), "UTF-8");
        }catch (IOException e) {
            result = "";
        }
        return result;
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
        String result;
        String token = wxConfigService.updateAccessToken();
        String url = String.format(
            API_MATERIAL_TOTLE,
            token
        );
        RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @Override
    public String getTypeList() {
        return null;
    }
}
