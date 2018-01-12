package com.hqhx.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener,HttpSessionAttributeListener{

	//预处理程序：当session被创建时调用该方法
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session被创建了:"+se.getSession().getId());
		//获取application
		ServletContext application=se.getSession().getServletContext();
		//从application范围获取计数的变量
		Integer c=(Integer) application.getAttribute("count");
		if(c==null){
			//如果c为null说明当前用户是第一个访问该网站的人
			application.setAttribute("count", 1);
		}else{
			application.setAttribute("count", c+1);
		}
	}
	//预处理程序：当session被销毁时调用该方法
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session被销毁了:"+se.getSession().getId());
		//获取application
		ServletContext application=se.getSession().getServletContext();
		//从application范围获取计数的变量
		Integer c=(Integer) application.getAttribute("count");
		if(c!=null){
			application.setAttribute("count", c-1);
		}
	}
	//当给session中添加属性后就调用该方法
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	//当给session中移除属性后就调用该方法
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	//当给session中修改属性后就调用该方法
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

}
