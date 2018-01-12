package com.hqhx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hqhx.dao.DeptDao;
import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.util.DBHelper;

public class DeptDaoImpl implements DeptDao{
	private DBHelper db=new DBHelper();
	@Override
	public int addDept(Dept dept) {
		String sql="insert into dept (deptno,dname,loc) values (?,?,?)";
		int i=db.CUD(sql, dept.getDeptno(),dept.getDname(),dept.getLoc());
		return i;
	}

	@Override
	public int deleteDeptById(Integer deptno) {
		String sql="delete from dept where deptno=?";
		int i=db.CUD(sql, deptno);
		return i;
	}

	@Override
	public int updateDept(Dept dept) {
		String sql="update dept set dname=?,loc=? where deptno=?";
		int i=db.CUD(sql, dept.getDname(),dept.getLoc(),dept.getDeptno());
		return i;
	}

	@Override
	public List<Dept> listDept() {
		String sql="select deptno,dname,loc from dept";
		List<Dept> depts=db.query(sql, Dept.class);
		return depts;
	}

	@Override
	public Dept findDeptById(Integer deptno) {
		String sql="select deptno,dname,loc from dept where deptno=?";
		System.out.println("------------->"+deptno);
		List<Dept> depts=db.query(sql, Dept.class, deptno);
		if(depts.size()>0){
			return depts.get(0);
		}
		return null;
	}

	@Override
	public void listDeptByPager(Pager<Dept> pager) {
		String sql="select deptno,dname,loc from dept limit ?,?";
		//查询当前页的部门信息
		List<Dept> depts=db.query(sql, Dept.class, pager.getOffSet(),pager.getPageSize());
		//把当前页部门信息存储到pager对象中
		pager.setDatas(depts);
	}

	@Override
	public Long getTotalCount() {
		long totalCount=0;
		String sql="select count(deptno) from dept";
		Connection conn=db.getConn();
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				totalCount=rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}
}
