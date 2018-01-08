package com.hqhx.dao.impl;

import java.util.List;

import com.hqhx.dao.DeptDao;
import com.hqhx.model.Dept;
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
}
