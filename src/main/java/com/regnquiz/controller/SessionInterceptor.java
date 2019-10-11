package com.regnquiz.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        if(request.getRequestURI().equals("/")) {
            try{
                if ((Integer) session.getAttribute("userType") == 2) {
                    System.out.println("Staff");
                    response.sendRedirect("/staff/"+session.getAttribute("userID"));
                } else if ((Integer) session.getAttribute("userType") == 3) {
                    System.out.println("Student");
                    response.sendRedirect("/student/"+session.getAttribute("userID"));
                }
            }catch (NullPointerException e){
                response.sendRedirect("/login");
            }
        }
        return true;
    }
    /*@Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {}*/
}
