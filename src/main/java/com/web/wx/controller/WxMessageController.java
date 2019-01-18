package com.web.wx.controller;

import com.web.wx.util.WxRes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */
@RestController
@RequestMapping("/msg")
public class WxMessageController {

    @RequestMapping(value = "/responseMsg", method = RequestMethod.POST)
    public WxRes responseMsg() {
        return null;
    }

    @RequestMapping(value = "/receiveEvent", method = RequestMethod.POST)
    public WxRes receiveEvent(HttpServletRequest request) {
        //响应消息
        String responseMessage="";
        //得到消息类型
        System.out.println(request);
        return null;
    }
}
