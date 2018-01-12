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
	
	//1.构造方法
	public DeptServlet(){
		System.out.println("DeptServlet构造方法");
	}
	
	//2.调用方法初始化Servlet
	@Override
	public void init() throws ServletException {
		System.out.println("---------DeptServlet的init方法-----");
	}
	
	//3.调用service(HttpServletRequest req,HttpServletResponse resp)
	//m=req.getMethod()
	//判断m是get，post,如果是get，内部调用doGet的方法，并
	//判断m是get，post,如果是post，内部调用doPost的方法，并
	
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
	
	//分页查询
	private void listDeptByPager(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		//获取客户端传递的当前页
		String currentPage=req.getParameter("currentPage");
		Pager<Dept> pager=new Pager<Dept>();
		//客户端如果传递了页码则把页码设置给pager对象，如果没有传递，不用设置使用默认的当前页1
		if(currentPage!=null){
			pager.setCurrentPage(Integer.parseInt(currentPage));
		}
		deptService.listDeptByPager(pager);
		req.setAttribute("pager", pager);
		req.getRequestDispatcher("listDeptByPager.jsp").forward(req, resp);
	}

	//修改部门
	private void updateDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		String dname=req.getParameter("dname");
		String loc=req.getParameter("loc");
		Dept dept=new Dept(Integer.parseInt(deptno),dname,loc);
		int i=deptService.updateDept(dept);
		listDept(req, resp);
	}

	//根据id查询部门
	private void findDeptById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		System.out.println(deptno);
		Dept dept=deptService.findDeptById(Integer.parseInt(deptno));
		//跳转到修改页面并共享数据
		req.setAttribute("dept", dept);
		//请求转发到updateDept.jsp
		req.getRequestDispatcher("updateDept.jsp").forward(req, resp);
	}

	//根据id删除部门
	private void deleteDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno=req.getParameter("deptno");
		int i=deptService.deleteDeptById(Integer.parseInt(deptno));
		listDept(req, resp);
	}

	//查询部门
	public void listDept(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		List<Dept> depts=deptService.listDept();
		//响应到listDept.jsp并共享数据
		req.setAttribute("depts", depts);
		//请求转发
		req.getRequestDispatcher("listDept.jsp").forward(req, resp);
	}
	
	
	//添加部门
	public void addDept(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String deptno=req.getParameter("deptno");
		String dname=req.getParameter("dname");
		String loc=req.getParameter("loc");
		Dept dept=new Dept(Integer.parseInt(deptno),dname,loc);
		//调用业务逻辑层
		int i=deptService.addDept(dept);
		//根据业务逻辑层的反馈，给客户响应
		if(i>0){
			//成功则跳转到展示页面
			String msg="部门添加成功";
			//把msg存储到request范围
			req.setAttribute("msg", msg);
			//通过请求转发，请求转发共享request对象的
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		}else{
			//失败跳转到添加页面并给出提示
			String msg="添加部门失败，请重新添加";
			//把msg存储到request范围
			req.setAttribute("msg", msg);
			//通过请求转发，请求转发共享request对象的
			req.getRequestDispatcher("addDept.jsp").forward(req, resp);			
		}
	}
	
	
	
	//6.调用destory方法销毁servlet
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("------------销毁-----------");
	}
	
}
