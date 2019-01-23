package com.web.wx.config;

import com.web.wx.util.WxMessageUtil;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */

public class WxService {

    public static String doService(ServletRequest request) {
        //定义要返回的XML
        String xml = "";

        Map<String, String> message = WxMessageUtil.parseXml(request);

        String event = message.get("Event");
        String msgType = message.get("MsgType");
        String content = message.get("Content");

        //推送事件
        if(msgType.equals(WxMessageUtil.MESSAGE_EVENT)) {
            //首次关注
            if(event.equals(WxMessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                String msg = WxMessageUtil.resSubscribeMsg();
                xml = WxMessageUtil.resMsgXml(message, msg);
                return xml;
            }else{
                return xml;
            }
        }else{
            if(content.matches("饿")) {


            }else if(content.matches("困")) {

            }else if(content.matches("看")) {

            }else if(content.matches("游")) {

            }else if(content.matches("唱")) {

            }else if(content.matches("美")) {

            }else if(content.matches("买")) {

            }else if(content.matches("玩")) {

            }else if(content.matches("脚")) {

            }else if(content.matches("车")) {

            }else if(content.matches("新")) {

            }else{
                String msg = WxMessageUtil.resTemplateMsg();
                xml = WxMessageUtil.resMsgXml(message, msg);
            }
            return xml;
        }

    }

}
