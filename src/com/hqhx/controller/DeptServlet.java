package com.hqhx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.service.DeptService;
import com.hqhx.service.impl.DeptServiceImpl;

public class DeptServlet extends HttpServlet{

	//init doGet doPost Service destory
	private DeptService deptService=new DeptServiceImpl();
	
	//1.���췽��
	public DeptServlet(){
		System.out.println("DeptServlet���췽��");
	}
	
	//2.���÷�����ʼ��Servlet
	@Override
	public void init() throws ServletException {
		System.out.println("---------DeptServlet��init����-----");
	}
	
	//3.����service(HttpServletRequest req,HttpServletResponse resp)
	//m=req.getMethod()
	//�ж�m��get��post,�����get���ڲ�����doGet�ķ�������
	//�ж�m��get��post,�����post���ڲ�����doPost�ķ�������
	
	//4,5
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("---------doGet---------");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("---------doPost----------");
		String m=req.getParameter("m");
		switch(m){
		case "addDept":
			addDept(req, resp);
			break;
		case "listDept":
			listDept(req, resp);
			break;
		case "deleteDept":
			deleteDept(req,resp);
			break;
		case "findDeptById":
			findDeptById(req,resp);
			break;
		case "updateDept":
			updateDept(req,resp);
			break;
		case "listDeptByPager":
			listDeptByPager(req,resp);
		}
	}
	
	//��ҳ��ѯ
	private void listDeptByPager(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�ͻ��˴��ݵĵ�ǰҳ
		String currentPage=req.getParameter("currentPage");
		Pager<Dept> pager=new Pager<Dept>();
		//�ͻ������������ҳ�����ҳ�����ø�pager�������û�д��ݣ���������ʹ��Ĭ�ϵĵ�ǰҳ1
		if(currentPage!=null){
			pager.setCurrentPage(Integer.parseInt(currentPage));
		}
		deptService.listDeptByPager(pager);
		req.setAttribute("pager", pager);
		req.getRequestDispatcher("listDeptByPager.jsp").forward(req, resp);
	}

	//�޸Ĳ���
	private void updateDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		String dname=req.getParameter("dname");
		String loc=req.getParameter("loc");
		Dept dept=new Dept(Integer.parseInt(deptno),dname,loc);
		int i=deptService.updateDept(dept);
		listDept(req, resp);
	}

	//����id��ѯ����
	private void findDeptById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		System.out.println(deptno);
		Dept dept=deptService.findDeptById(Integer.parseInt(deptno));
		//��ת���޸�ҳ�沢��������
		req.setAttribute("dept", dept);
		//����ת����updateDept.jsp
		req.getRequestDispatcher("updateDept.jsp").forward(req, resp);
	}

	//����idɾ������
	private void deleteDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		int i=deptService.deleteDeptById(Integer.parseInt(deptno));
		listDept(req, resp);
	}

	//��ѯ����
	public void listDept(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		List<Dept> depts=deptService.listDept();
		//��Ӧ��listDept.jsp����������
		req.setAttribute("depts", depts);
		//����ת��
		req.getRequestDispatcher("listDept.jsp").forward(req, resp);
	}
	
	
	//��Ӳ���
	public void addDept(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String deptno=req.getParameter("deptno");
		String dname=req.getParameter("dname");
		String loc=req.getParameter("loc");
		Dept dept=new Dept(Integer.parseInt(deptno),dname,loc);
		//����ҵ���߼���
		int i=deptService.addDept(dept);
		//����ҵ���߼���ķ��������ͻ���Ӧ
		if(i>0){
			//�ɹ�����ת��չʾҳ��
			String msg="������ӳɹ�";
			//��msg�洢��request��Χ
			req.setAttribute("msg", msg);
			//ͨ������ת��������ת������request�����
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		}else{
			//ʧ����ת�����ҳ�沢������ʾ
			String msg="��Ӳ���ʧ�ܣ����������";
			//��msg�洢��request��Χ
			req.setAttribute("msg", msg);
			//ͨ������ת��������ת������request�����
			req.getRequestDispatcher("addDept.jsp").forward(req, resp);			
		}
	}
	
	
	
	//6.����destory��������servlet
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("------------����-----------");
	}
	
}
