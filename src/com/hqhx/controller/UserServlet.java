package com.hqhx.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hqhx.model.User;
import com.hqhx.service.UserService;
import com.hqhx.service.impl.UserServiceImpl;
import com.hqhx.util.CreateImage;

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
		case "createImage":
			createImage(req,resp);
		default:
			break;
		}
	}

	private void createImage(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("****************");
		//设置响应的数据类型
		resp.setContentType("image/jpg");
		CreateImage c=new CreateImage();
		//获取图片
		BufferedImage img=c.getImage();
		String trueCode=c.getCode();
		req.getSession().setAttribute("trueCode", trueCode);
		//把code存储到session中
		OutputStream os=null;
		//获取输出流
		try {
			os=resp.getOutputStream();
			ImageIO.write(img, "JPEG", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		//获取真实的验证码
		String trueCode=(String)req.getSession().getAttribute("trueCode");	
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
				//判断验证码是否正确
				if(code.equalsIgnoreCase(trueCode)){					
					//密码正确，登录成功
					//获取session
					HttpSession session=req.getSession();
					//把当前用户存储到session中
					session.setAttribute("user", user);
					//把用户名和密码响应给客户端，让客户端把用户名和密码存储到客户端的Cookie文件中
					Cookie uname=new Cookie("username",URLEncoder.encode(user.getUsername(),"UTF-8"));
					Cookie psw=new Cookie("password",user.getPassword());
					if("1".equals(isM)){
						//设置用户名和密码在cookie文件中的保存时间
						uname.setMaxAge(7*24*60*60*1000);
						psw.setMaxAge(7*24*60*60*1000);
					}else{
						uname.setMaxAge(0);
						psw.setMaxAge(0);
					}
					resp.addCookie(uname);
					resp.addCookie(psw);
					resp.sendRedirect("index.jsp");
				}else{
					//登录失败，提示验证码错误，请重新登录
					msg="验证码错误，请重新登录";
					//把msg设置到request范围
					req.setAttribute("msg", msg);
					//请求转发到login.jsp
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
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
