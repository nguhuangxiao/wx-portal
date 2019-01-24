package com.web.wx.service;

import com.web.wx.dto.MaterialDto;
import com.web.wx.dto.MaterialListDto;
import com.web.wx.req.MaterialReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/23
 */
public interface WxMaterialService {

    /** 新增临时素材 **/
    String addTemporary(MultipartFile file);

    /** 临时素材列表 **/
    String temporaryList();

    /** 新增永久列表 **/
    String addPermanent(MaterialReq materialReq);

    /** 永久素材列表 **/
    String permanentList();

    /** 删除**/
    String deletePermanent();

    String updatePermanent();

    /** 获取素材总数 **/
    String getTotle();

    /** 类型列表 **/
    String getTypeList();

}
