package org.javapatil.studproject.util.security.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javapatil.studproject.util.security.admin.SessionHandler;
import org.javapatil.studproject.util.security.admin.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * *********************************************
 * Date         Name                         
 * *********************************************
 * 11-Dec-2019   Sachin Patil aka JavaPatil  
 */

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    private List<String> bypassUrlList;
    private SessionHandler sessionHandler;

    public void setBypassUrlList(List<String> bypassUrlList) {
        this.bypassUrlList = bypassUrlList;
    }

    public void setSessionHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean processRequest = false;
        String servletPath = request.getServletPath();

        if (servletPath.contains("/v1") && !request.getMethod().equalsIgnoreCase("OPTIONS")) {
        //if (servletPath.contains("") && !request.getMethod().equalsIgnoreCase("OPTIONS")) {
            if (bypassUrlList.contains(servletPath)) {
                processRequest = true;
            } else {
                User user = sessionHandler.validateToken(request.getHeader("access-token"));
                if (user == null)
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                else {
                    processRequest = true;
                    request.setAttribute("user", user);
                }
            }
        } else {
        	//System.out.println("Inside elseeee part");
            processRequest = true;                    
        }
        return processRequest;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
