package com.how2java.interceptor;

import com.github.pagehelper.util.StringUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor  extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o){
        String[] noNeedAuthPage = new String[]{
                "home",
                "checkLogin",
                "register",
                "loginAjax",
                "login",
                "product",
                "category",
                "search"};
        HttpSession session=request.getSession();
        String contentPath=session.getServletContext().getContextPath();
        String uri=request.getRequestURI();
        uri= StringUtils.remove(uri,contentPath);
        if (uri.startsWith("/fore")){
            uri=StringUtils.substringAfterLast(uri,"/fore");

        }
        return false;
    }
}
