package com.web.wx.filter;

import com.web.wx.config.WxService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 微信消息过滤
 * @Author: nguhuangxiao
 * @Date: 2019/1/21
 */
@WebFilter(filterName = "wxMsgFilter", urlPatterns = "/*")
@Slf4j
public class WxMsgFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter初始化");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter 请求处理");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
        System.out.println(basePath);

        //输入格式，否则会出现?错误
        chain.doFilter(req, res);

        /*req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        try{
            //处理消息
            String xmlString = WxService.doService(req);
            out.print(xmlString);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.close();
        }*/
    }

    @Override
    public void destroy() {
        log.info("filter 销毁");
    }
}
