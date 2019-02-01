package com.web.wx.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/25
 */
@Data
public class NewsReq {

    @NotNull(message = "媒体类型")
    private String type;

    @NotNull(message = "偏移位")
    private int offset;

    @Range(min=1, max=20, message = "每页条数")
    private int count;

}
