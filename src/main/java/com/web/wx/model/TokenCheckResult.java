package com.web.wx.model;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2018/12/17
 */
@Data
@ToString
public class TokenCheckResult {

    private boolean success;

    private Claims claims;

    private String errorcode;

}
