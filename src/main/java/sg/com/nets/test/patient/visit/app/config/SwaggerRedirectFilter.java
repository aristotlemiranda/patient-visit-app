package sg.com.nets.test.patient.visit.app.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/***
 *@author Miranda Aristotle
 **/

@Component
public class SwaggerRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String group = req.getParameter("group");

        if (req.getServletPath().equals("/swagger-ui") 
        		|| req.getServletPath().equals("/api/test")
        		|| req.getServletPath().equals("/test")
        		&& group==null) {
            res.sendRedirect("/swagger-ui/");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

	

}