package com.web.wx.config;

import com.web.wx.dto.WxMsgEventDto;
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

        String event = message.get("Event");
        String msgType = message.get("MsgType");


        //推送事件
        if(msgType.equals(WxMessageUtil.MESSAGE_EVENT)) {
            //首次关注
            if(event.equals(WxMessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                System.out.println("********************ceshi*************");
                String msg = WxMessageUtil.resSubscribeMsg();
                xml = WxMessageUtil.resMsgXml(message, msg);
                return xml;
            }else{
                return xml;
            }
        }else{
            xml = WxMessageUtil.resMsgXml(message, "测试");
            return xml;
        }


    }

}
