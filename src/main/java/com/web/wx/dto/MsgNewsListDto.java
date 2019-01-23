package com.web.wx.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/22
 */
@Data
public class MsgNewsListDto extends MsgBaseDto {

    private Integer ArticleCount;

    private List<MsgNewDto> Articles;

}
