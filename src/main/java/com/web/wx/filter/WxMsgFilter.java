package com.web.wx.filter;

import com.web.wx.config.WxService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */
@WebServlet(urlPatterns = "/*")
public class WxMsgFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String xmlString = WxService.doService(req);
        PrintWriter out= res.getWriter();
        out.print(xmlString);
        out.close();
    }


}
