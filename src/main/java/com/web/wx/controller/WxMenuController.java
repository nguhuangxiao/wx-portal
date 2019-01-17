package com.web.wx.controller;

import com.web.wx.service.WxMenuService;
import com.web.wx.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/17
 */
@RestController
@RequestMapping("/menu")
public class WxMenuController {

    @Autowired
    private WxMenuService wxMenuService;

    /**
     * 菜单创建
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Res add() {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Res list() {
        String res = wxMenuService.list();
        return Res.buildOk(res);
    }
}
