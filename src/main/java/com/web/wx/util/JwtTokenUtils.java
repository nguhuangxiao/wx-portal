package com.web.wx.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.web.wx.constant.Token;
import com.web.wx.model.TokenCheckResult;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @Description: token分装
 * @Author: nguhuangxiao
 * @Date: 2018/12/17
 */
public class JwtTokenUtils {

    //过去时间 1小时
    public static final long EXPIRATION = 3600L;

    //记住-7天
    public static final long EXPIRATION_REMEMBER = 604800L;

    //生成私钥
    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.decode(Token.JWT_ENCRYPT);
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 生成token，该方法只在用户登录成功后调用
     * @param payload
     * @return
     */
    public static String createJwtToken(Map<String, Object> payload) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //设置过期时间
        Date expiration = new Date(nowMillis + EXPIRATION * 1000);
        //加盐
        SecretKey secretKey = generalKey();
        String id = payload.get("phone").toString();
        String subject = payload.get("subject").toString();
        JwtBuilder builder = Jwts.builder()
            .setClaims(payload)
            .setId(id)
            //签发用户
            .setSubject(subject)
            //签发时间
            .setIssuedAt(now)
            //签名算法
            .signWith(signatureAlgorithm, secretKey)
            //设置失效时间
            .setExpiration(expiration);

        return  builder.compact();

    }

    /**
     * 验证TOKEN
     * @param tokenStr
     * @return
     */
    public static TokenCheckResult validateJWT(String tokenStr) {

        TokenCheckResult checkResult = new TokenCheckResult();
        Claims claims = null;
        try {
            claims = parseTokenStr(tokenStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrorcode(Token.JWT_ERR_CODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrorcode(Token.JWT_ERR_CODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrorcode(Token.JWT_ERR_CODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 解析JWT字符串
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseTokenStr(String token) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }


}
