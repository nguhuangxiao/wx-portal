package com.web.wx.req;

import com.web.wx.dto.MaterialDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/25
 */
@Data
public class MaterialItemReq {

    @NotNull
    private String media_id;

    @NotNull
    private int index;

    private MaterialDto articles;

}
