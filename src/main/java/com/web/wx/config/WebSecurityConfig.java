package com.web.wx.config;


import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/3
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * 登录session key
     */
    public final static String GLOBAL_USER_INFO = "user_info";

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        //addInterceptor.excludePathPatterns("/**");

        // 拦截配置
        //addInterceptor.addPathPatterns("/**");

        //排除配置
        addInterceptor.excludePathPatterns("/**");
        //addInterceptor.excludePathPatterns("/user/login");

    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            //获取session
            Object userObj = request.getSession().getAttribute(GLOBAL_USER_INFO);
            if(userObj != null){
                return true;
            }
            Map<String,Object> result = new HashMap<>();
            result.put("status", -1);
            result.put("message","to login");
            responseOutWithJson(response,result);
            return false;
        }
    }

    public static void responseOutWithJson(HttpServletResponse response, Object responseObject) {
        // 将实体对象转换为JSON Object转换
        String responseJSONObject = JSON.toJSONString(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject);
        } catch (IOException e) {
            logger.warn("responseOutWithJson",e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
