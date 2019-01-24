package com.web.wx.controller;

import com.web.wx.constant.ResponceMessage;
import com.web.wx.dto.MaterialDto;
import com.web.wx.dto.MaterialListDto;
import com.web.wx.req.MaterialReq;
import com.web.wx.service.WxMaterialService;
import com.web.wx.util.Res;
import com.web.wx.util.WxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "/addPermanent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WxRes addPermanent(@RequestBody @Valid MaterialReq materialReq, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMsg = fieldError.getDefaultMessage();
            return WxRes.buildFail(errorMsg);
        }
        String msg = wxMaterialService.addPermanent(materialReq);
        return null;
    }

}
