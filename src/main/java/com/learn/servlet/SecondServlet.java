package com.learn.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet和jsp的区别
 * 1.Servlet中没有内置对象，原来JSP中的内置对象都必须由程序显示创建
 * 2.对于静态的html标签，servlet都必须使用页面输出流逐行输出
 * http://localhost:8090/shipinfo/secondServlet?name=zhangsan&age=11
 */
public class SecondServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("-------------SecondServlet init----------------");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println("name = " + name + ",age = " + age);
        ServletConfig config = getServletConfig();
        System.out.println("SecondServlet driver = " + config.getInitParameter("driver"));
        System.out.println("SecondServlet url = " + config.getInitParameter("url"));

    }

    @Override
    public void destroy() {
        System.out.println("-----------SecondServlet destroy----------------");
    }
}
