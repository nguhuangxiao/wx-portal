package com.web.wx.mapper;

import com.web.wx.model.WxAccessToken;

public interface WxAccessTokenMapper {

    int insert(WxAccessToken record);

    WxAccessToken selectByPrimaryKey(Long gid);

    WxAccessToken findLatest();

}
