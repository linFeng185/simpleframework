package com.lin.controller;

import com.lin.controller.frontend.MainPageController;
import com.lin.controller.superadmin.HeadLineOperationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截所有的请求并解析，再派发给对应的controller处理请求
 * /和/*的区别是，/是优先级最低的拦截，属于路径匹配，并且匹配所有的request，由于路径匹配的优先级仅次于精确匹配，所以/*会覆盖所有的扩展名匹配，
 * 也就是说会覆盖掉*.jsp的匹配，导致出现死循环调用
 * @author lin
 * @date 2022/1/13 22:46
 **/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    public static String POST = "POST";
    public static String GET = "GET";
    public static String PUT = "PUT";
    public static String DELETE = "DELETE";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String method = req.getMethod();
        System.out.println("request path is:"+servletPath);
        System.out.println("request method is:"+method);
        if("/frontend/getmainpageinfo".equals(servletPath) && GET.equals(method)){
            new MainPageController().getMainPageInfo(req,resp);
        }else if ("/superadmin/addheadline".equals(servletPath) && POST.equals(method)){
            new HeadLineOperationController().addHeadLine(req,resp);
        }
    }
}
