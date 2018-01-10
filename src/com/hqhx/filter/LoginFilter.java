package com.hqhx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hqhx.model.User;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		StringBuffer url=req.getRequestURL();
		System.out.println(url);
		//http://localhost:8080/EL_JSTL20180105/login.jsp
		
		String uri=url.substring(url.lastIndexOf("/")+1);
		System.out.println("----------------------");
		System.out.println(uri);
		String m=req.getParameter("m");
		if(uri.equals("")||uri.equals("login.jsp")||(uri.equals("user")&&m.equals("login"))){
			//当访问登录页面或者登录时不能拦截
			chain.doFilter(req, response);
		}else{
			//判断用户是否登录了，
			HttpSession session=req.getSession();
			User user=(User) session.getAttribute("user");
			if(user!=null){
				//如果登录了就继续访问目标资源
				chain.doFilter(req, response);
			}else{
				//如果没有登录则响应到登录页面
				//响应到登录页面，并且提示用户先登录
				String msg="请先登录";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("login.jsp").forward(req, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
