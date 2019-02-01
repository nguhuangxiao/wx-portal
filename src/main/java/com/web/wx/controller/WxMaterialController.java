package com.web.wx.controller;

import com.web.wx.constant.ResponceMessage;
import com.web.wx.constant.WxMaterial;
import com.web.wx.req.MaterialItemReq;
import com.web.wx.req.MaterialListReq;
import com.web.wx.req.NewsReq;
import com.web.wx.service.WxMaterialService;
import com.web.wx.util.WxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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
    public WxRes addTemporary(MultipartFile file) {
        if(file == null) {
           return WxRes.buildFail(ResponceMessage.NOT_FILE);
        }
        String msg =  wxMaterialService.addTemporary(file);
        return WxRes.buildRes(msg);
    }


    @RequestMapping(value = "/addPermanent", method = RequestMethod.POST)
    public WxRes addPermanent(MultipartFile file) {
        if(file == null) {
            return WxRes.buildFail(ResponceMessage.NOT_FILE);
        }
        String msg = wxMaterialService.addPermanent(file);
        return WxRes.buildRes(msg);
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public WxRes getFile(@RequestParam(value = "type") String type, @RequestParam(value = "mediaId") String mediaId) {
        String msg = wxMaterialService.downLoad(type, mediaId);
        return WxRes.buildRes(msg);
    }


    @RequestMapping(value = "/addNews", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WxRes addNews(@RequestBody @Valid MaterialListReq materialListReq, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMsg = fieldError.getDefaultMessage();
            return WxRes.buildFail(errorMsg);
        }
        String msg = wxMaterialService.updateNews(materialListReq, WxMaterial.ADD_NEWS);
        return WxRes.buildRes(msg);
    }

    @RequestMapping(value = "/updateNews", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WxRes updateNews(@RequestBody @Valid MaterialItemReq materialItemReq, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMsg = fieldError.getDefaultMessage();
            return WxRes.buildFail(errorMsg);
        }
        String msg = wxMaterialService.updateNews(materialItemReq, WxMaterial.UPDATE_NEWS);
        return WxRes.buildRes(msg);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WxRes materialList(@Valid NewsReq newsReq, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMsg = fieldError.getDefaultMessage();
            return WxRes.buildFail(errorMsg);
        }
        String msg = wxMaterialService.list(newsReq);
        return WxRes.buildRes(msg);
    }

    @RequestMapping(value = "/totle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WxRes totle() {
        String msg = wxMaterialService.getTotle();
        return WxRes.buildRes(msg);
    }

}
