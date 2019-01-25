package com.web.wx.service;

import com.web.wx.req.MaterialItemReq;
import com.web.wx.req.MaterialListReq;
import com.web.wx.req.NewsReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/23
 */
public interface WxMaterialService {

    /** 新增临时素材 **/
    String addTemporary(MultipartFile file);

    /** 新增永久素材 **/
    String addPermanent(MultipartFile file);

    /** 下载素材（临时和永久）**/
    String downLoad();

    /** 新增修改图文 **/
    String updateNews(Object materialDto, int type);

    /** 图文列表 **/
    String list(NewsReq newsReq);

    /** 删除**/
    String deletePermanent();

    String updatePermanent();

    /** 获取素材总数 **/
    String getTotle();

    /** 类型列表 **/
    String getTypeList();

}
