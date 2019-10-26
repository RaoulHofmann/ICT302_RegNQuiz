package com.regnquiz.controller.config;

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
                if ((Integer) session.getAttribute("userType") == 1) {
                    response.sendRedirect("/admin/"+session.getAttribute("userID"));
                } else if ((Integer) session.getAttribute("userType") == 2) {
                    response.sendRedirect("/staff/"+session.getAttribute("userID"));
                } else if ((Integer) session.getAttribute("userType") == 3) {
                    response.sendRedirect("/student/"+session.getAttribute("userID"));
                }
            }catch (NullPointerException e){
                response.sendRedirect("/login");
            }
        }

        if(request.getRequestURI().equals("/staff")) {
            try{
                if ((Integer) session.getAttribute("userType") == 2) {
                    System.out.println("Staff");
                    response.sendRedirect("/staff/"+session.getAttribute("userID"));
                }
            }catch (NullPointerException e){
                response.sendRedirect("/login");
            }
        }
        return true;
    }
}
