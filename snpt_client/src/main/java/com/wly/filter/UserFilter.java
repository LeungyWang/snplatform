package com.wly.filter;

import com.wly.entity.Role;
import com.wly.entity.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
@WebFilter(urlPatterns = {"/land/redirect/index2"}, filterName = "userFilter")
public class    UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            response.sendRedirect("/land/redirect/login");
        }else {
            List<Role> roles = (List<Role>) session.getAttribute("roles");
            Boolean flag = false;
            for (int i=0;i<roles.size();i++){
                Role role = roles.get(i);
                if (role.getRolecode().equals("farmer")){
                    flag = true;
                }
            }
            if (!flag){
                response.sendRedirect("/land/redirect/error403");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }



}
