package com.web.wx.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
public class WxProp {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

}
