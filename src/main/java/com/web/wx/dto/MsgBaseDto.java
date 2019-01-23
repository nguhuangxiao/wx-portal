package com.web.wx.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */
@Data
public class MsgBaseDto {

    /**
     * 开发者微信号
     */
    public String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    public String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    public long CreateTime;
    /**
     * text
     */
    public String MsgType ;

}
