//package com.wly.filter;
//
//import com.wly.entity.Role;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@Component
//@WebFilter(urlPatterns = {"/land/redirect/index2"},filterName = "farmerFilter")
//public class FarmerFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession();
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
