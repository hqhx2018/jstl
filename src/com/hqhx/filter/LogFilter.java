package com.hqhx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("--------LogFilter销毁-------");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("-------doFilter开始访问---------------");
		//调用过滤器链中下一个过滤器，如果没有就调用目标资源
		chain.doFilter(req, resp);
		System.out.println("-------doFilter结束访问----------");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("--------LogFilter初始化-------");
	}

}
