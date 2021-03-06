package com.lin;



import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lin
 * @date 2022/1/12 22:06
 **/
@WebServlet("/hello")
@Slf4j
public class HelloServlet extends HttpServlet {

    @Override
    public  void init(){
        System.out.println("初始化Servlet ...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("是我执行了doGet方法，我才是入口");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架";
        log.debug("debut日志打印");
        req.setAttribute("name",name);
        req.getRequestDispatcher("WEB-INFO/jsp/hello.jsp").forward(req,resp);
    }

    @Override
    public  void destroy(){
        System.out.println("Destroy...");
    }
}
