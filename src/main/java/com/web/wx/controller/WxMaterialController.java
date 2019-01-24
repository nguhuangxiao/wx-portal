package com.web.wx.controller;

import com.web.wx.service.WxMaterialService;
import com.web.wx.util.WxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 素材管理
 * @Author: nguhuangxiao
 * @Date: 2019/1/23
 */
@RestController
@RequestMapping("/material")
public class WxMaterialController {

    @Autowired
    private WxMaterialService wxMaterialService;

    @RequestMapping(value = "/addTemporary", method = RequestMethod.POST)
    public WxRes addTemporary() {
       String msg =  wxMaterialService.addTemporary();
       return WxRes.buildRes(msg);
    }

}
