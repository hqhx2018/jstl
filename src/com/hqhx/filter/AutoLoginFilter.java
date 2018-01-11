package com.hqhx.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqhx.model.User;
import com.hqhx.service.UserService;
import com.hqhx.service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */

public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String username="";
		String password="";
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		Cookie[] cookies=req.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				//out.print(cookie.getName()+" "+URLDecoder.decode(cookie.getValue(),"UTF-8")+" "+cookie.getMaxAge()+" "+cookie.getPath());
				if(cookie.getName().equals("username")){
					username=URLDecoder.decode(cookie.getValue(),"UTF-8");
				}else if(cookie.getName().equals("password")){
					password=URLDecoder.decode(cookie.getValue(),"UTF-8");
				}
			}
			
			UserService userService=new UserServiceImpl();
			//判断cookie中是否保存用户名和密码
			if(username!=null&&!username.equals("")){
			User user=userService.login(username);
			if(user!=null&&user.getPassword().equals(password)){
				req.getSession().setAttribute("user", user);
				//resp.sendRedirect("index.jsp");
			}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
