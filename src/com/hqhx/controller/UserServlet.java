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
		default:
			break;
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
				//������ȷ����¼�ɹ�
				//��ȡsession
				HttpSession session=req.getSession();
				//�ѵ�ǰ�û��洢��session��
				session.setAttribute("user", user);
				resp.sendRedirect("index.jsp");
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
