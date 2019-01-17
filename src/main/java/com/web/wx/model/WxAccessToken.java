package com.web.wx.model;

import lombok.Data;

import java.util.Date;


@Data
public class WxAccessToken {

    private Long gid;

    private String token;

    private String appId;

    private String appSecret;

    private int expiresIn;

    private Date createTime;

}
