package com.web.wx.req;

import com.web.wx.dto.MaterialDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/24
 */
@Data
public class MaterialReq {

    @NotNull
    @ApiModelProperty(value = "素材", required = true)
    private List<MaterialDto> articles;

}
