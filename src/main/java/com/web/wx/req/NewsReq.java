package com.web.wx.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/25
 */
@Data
public class NewsReq {

    private String type;

    private int offset;

    @Range(min=1, max=20)
    private int count;

}
