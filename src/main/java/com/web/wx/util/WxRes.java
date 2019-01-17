package com.web.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.wx.dto.WxResDto;


/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/15
 */
public class WxRes {

    public WxRes() {
    }

    /**
     * 格式化微信返回结果
     * @param str
     * @return
     */
    public static WxResDto cb(String str) {
        JSONObject cb = JSONObject.parseObject(str);
        WxResDto wxResDto = new WxResDto();
        wxResDto.setErrCode(cb.getInteger("errcode"));
        wxResDto.setErrMsg(cb.getString("errmsg"));
        return wxResDto;
    }

    public static Object buildOk(String wxRes) {
        JSONObject res = JSON.parseObject(wxRes);
        return res;
    }


}
