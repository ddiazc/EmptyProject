package com.cgaf.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(AuthFilter.class);
     
    public AuthFilter() {}
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            String reqURI = req.getRequestURI();
            if ( reqURI.indexOf("/faces/template/login/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)) {
            	chain.doFilter(request, response);
            }
            else {
            	res.sendRedirect(req.getContextPath() + "/faces/template/login/login.xhtml");
            }
      }
         catch(Throwable t) {
        	 log.error("Error al procesar la solicitud de recurso .xhtml", t);
         }
     }
 
    @Override
    public void destroy() {}
    
}
