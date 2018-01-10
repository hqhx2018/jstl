package com.hqhx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hqhx.model.User;
import com.hqhx.service.UserService;
import com.hqhx.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet{

	//控制层依赖于业务逻辑层
	private UserService UserService=new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String m=req.getParameter("m");
		switch (m) {
		case "login":
			login(req,resp);
			break;
		case "logout":
			logout(req,resp);
		default:
			break;
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session=req.getSession();
		//销毁当前session
		session.invalidate();
		resp.sendRedirect("login.jsp");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求信息
		//用户名
		String username=req.getParameter("username");
		//密码
		String password=req.getParameter("password");
		//验证码
		String code=req.getParameter("code");
		//是否记住密码
		String isM=req.getParameter("isM");
		//根据用户名查询用户对象
		User user=UserService.login(username);
		String msg=null;
		if(user==null){
			//登录失败，提示用户用户名错误，请重新登录
			msg="用户用户名错误，请重新登录";
			//把msg设置到request范围
			req.setAttribute("msg", msg);
			//请求转发到login.jsp
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else{
			//用户名正确，验证密码是否正确
			if(user.getPassword().equals(password)){
				//密码正确，登录成功
				//获取session
				HttpSession session=req.getSession();
				//把当前用户存储到session中
				session.setAttribute("user", user);
				resp.sendRedirect("index.jsp");
			}else{
				//登录失败，提示用户密码错误，请重新登录
				msg="密码错误，请重新登录";
				//把msg设置到request范围
				req.setAttribute("msg", msg);
				//请求转发到login.jsp
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
	}
	
}
