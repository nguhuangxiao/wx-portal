package com.web.wx.service;

import com.web.wx.model.WxAccessToken;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/17
 */

public interface WxConfigService {

    /** 最新记录 **/
    WxAccessToken findLatest();

    /** 更新access_token **/
    String updateAccessToken();


}
