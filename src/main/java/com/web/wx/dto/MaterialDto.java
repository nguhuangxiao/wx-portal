package com.web.wx.dto;

import lombok.Data;


/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/24
 */
@Data
public class MaterialDto {

    private String title;

    private String thumbMediaId;

    private String author;

    /** 图文消息的摘要 **/
    private String digest;

    /** 封面 **/
    private Boolean showCoverPic;

    private String content;

    /** 阅读原文 **/
    private String contentSourceUrl;

    /** 打开评论 **/
    private Boolean needOpenComment;

    /** 粉丝评论 **/
    private Boolean onlyFansCanComment;

}
