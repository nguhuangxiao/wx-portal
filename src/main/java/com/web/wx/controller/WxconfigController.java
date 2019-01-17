package com.web.wx.controller;

import com.web.wx.service.WxConfigService;
import com.web.wx.util.CheckUtil;
import com.web.wx.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/17
 */
@RestController
@RequestMapping("/config")
public class WxconfigController {

    @Autowired
    private WxConfigService wxConfigService;

    /**
     * 微信测试号-服务器接口验证
     * 如果验证一致就将接收到的随机字符串返回给客户端
     * 如果不一致就返回空
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value = "portal", method = RequestMethod.GET)
    public String portal(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") String echostr) {
        //排序
        String sortString = CheckUtil.sort(timestamp, nonce);
        //加密
        String myString = CheckUtil.sha1(sortString);
        if (myString != null && myString != "" && myString.equals(signature)) {
            System.out.println("签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            System.out.println("签名校验失败");
            return "";
        }
    }

    @RequestMapping(value = "/updateToken", method = RequestMethod.GET)
    public Res updateToken() {
        wxConfigService.updateAccessToken();
        return null;
    }
}
