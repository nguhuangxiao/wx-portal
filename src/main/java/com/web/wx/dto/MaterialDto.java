package com.web.wx.dto;

import lombok.Data;


/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/24
 */
@Data
public class MaterialDto {

    private String author;

    /** 图文消息的摘要 **/
    private String digest;

    /** 封面 **/
    private Boolean show_cover_pic;

    private String content;

    /** 阅读原文 **/
    private String content_source_url;

    /** 打开评论 **/
    private Boolean need_open_comment;

    /** 粉丝评论 **/
    private Boolean only_fans_can_comment;

}
