package com.web.wx.config;

import com.web.wx.dto.WxMsgTextDto;
import com.web.wx.util.WxMessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */

public class WxService {

    public static String doService(HttpServletRequest request) {
        //定义要返回的XML
        String xml = "";

        Map<String, String> message = WxMessageUtil.parseXml(request);
        String messageType = message.get("MsgType");

        System.out.println("ToUserName：" + message.get("ToUserName"));
        System.out.println("FromUserName：" + message.get("FromUserName"));
        System.out.println("CreateTime：" + message.get("CreateTime"));
        System.out.println("MsgType：" + message.get("MsgType"));
        System.out.println("Content：" + message.get("Content"));
        System.out.println("MsgId：" + message.get("MsgId"));

        String content = message.get("Content");

        WxMsgTextDto msgDto = new WxMsgTextDto();
        msgDto.setToUserName(message.get("FromUserName"));
        msgDto.setFromUserName(message.get("ToUserName"));
        msgDto.setCreateTime(System.currentTimeMillis());
        msgDto.setContent(content);
        msgDto.setMsgType(messageType);

        xml = WxMessageUtil.textMessageToXml(msgDto);



        System.out.println("xml:"+xml);


        return xml;
    }

}
