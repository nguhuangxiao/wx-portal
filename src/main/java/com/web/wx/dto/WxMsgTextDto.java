package com.web.wx.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */
@Data
public class WxMsgTextDto extends WxMsgBase {

    public String Content;

    public long MsgId ;
}
