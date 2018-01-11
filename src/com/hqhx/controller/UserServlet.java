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

	//���Ʋ�������ҵ���߼���
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
		//������Ӧ����������
		resp.setContentType("image/jpg");
		CreateImage c=new CreateImage();
		//��ȡͼƬ
		BufferedImage img=c.getImage();
		String trueCode=c.getCode();
		req.getSession().setAttribute("trueCode", trueCode);
		//��code�洢��session��
		OutputStream os=null;
		//��ȡ�����
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
		//���ٵ�ǰsession
		session.invalidate();
		resp.sendRedirect("login.jsp");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ������Ϣ
		//�û���
		String username=req.getParameter("username");
		//����
		String password=req.getParameter("password");
		//��֤��
		String code=req.getParameter("code");
		//�Ƿ��ס����
		String isM=req.getParameter("isM");
		//�����û�����ѯ�û�����
		User user=UserService.login(username);
		//��ȡ��ʵ����֤��
		String trueCode=(String)req.getSession().getAttribute("trueCode");	
		String msg=null;
		if(user==null){
			//��¼ʧ�ܣ���ʾ�û��û������������µ�¼
			msg="�û��û������������µ�¼";
			//��msg���õ�request��Χ
			req.setAttribute("msg", msg);
			//����ת����login.jsp
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else{
			//�û�����ȷ����֤�����Ƿ���ȷ
			if(user.getPassword().equals(password)){
				//�ж���֤���Ƿ���ȷ
				if(code.equalsIgnoreCase(trueCode)){					
					//������ȷ����¼�ɹ�
					//��ȡsession
					HttpSession session=req.getSession();
					//�ѵ�ǰ�û��洢��session��
					session.setAttribute("user", user);
					//���û�����������Ӧ���ͻ��ˣ��ÿͻ��˰��û���������洢���ͻ��˵�Cookie�ļ���
					Cookie uname=new Cookie("username",URLEncoder.encode(user.getUsername(),"UTF-8"));
					Cookie psw=new Cookie("password",user.getPassword());
					if("1".equals(isM)){
						//�����û�����������cookie�ļ��еı���ʱ��
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
					//��¼ʧ�ܣ���ʾ��֤����������µ�¼
					msg="��֤����������µ�¼";
					//��msg���õ�request��Χ
					req.setAttribute("msg", msg);
					//����ת����login.jsp
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}else{
				//��¼ʧ�ܣ���ʾ�û�������������µ�¼
				msg="������������µ�¼";
				//��msg���õ�request��Χ
				req.setAttribute("msg", msg);
				//����ת����login.jsp
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
	}
	
}
