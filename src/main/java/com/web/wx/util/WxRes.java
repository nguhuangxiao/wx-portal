package com.web.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.wx.dto.WxResDto;
import lombok.Data;


/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/15
 */
@Data
public class WxRes<T> {

    private String status;
    private T result;
    private Integer code;
    private String message;

    public static final String OK = "ok";
    public static final String FAIL = "fail";

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

    public static WxRes buildOk(Object result) {
        WxRes wxRes = new WxRes();
        wxRes.setCode(0);
        wxRes.setStatus(OK);
        wxRes.setResult(result);
        return wxRes;
    }

    public static WxRes buildFail(Object result) {
        WxRes wxRes = new WxRes();
        wxRes.setCode(-1);
        wxRes.setStatus(FAIL);
        wxRes.setResult(result);
        return wxRes;
    }

    public static WxRes buildRes(String resStr) {
        JSONObject res = JSONObject.parseObject(resStr);
        Object obj = res.get("errcode");
        if(obj == null) {
            return buildOk(res);
        }else{
            if(obj.equals(0)) {
                return buildOk(res);
            }else{
                return buildFail(res);
            }
        }
    }

}
