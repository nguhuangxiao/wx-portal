package com.web.wx.dto;

import lombok.Data;

@Data
public class WxMsgEventDto extends WxMsgBase {

    public String Content;

    private String MsgId;

}
