package com.hqhx.service.impl;

import java.util.List;

import com.hqhx.dao.DeptDao;
import com.hqhx.dao.impl.DeptDaoImpl;
import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.service.DeptService;

public class DeptServiceImpl implements DeptService{

	private DeptDao deptDao=new DeptDaoImpl();
	
	@Override
	public int addDept(Dept dept) {
		if(dept!=null){
			return deptDao.addDept(dept);
		}
		return 0;
	}

	@Override
	public int deleteDeptById(Integer deptno) {
		if(deptno!=null){
			return deptDao.deleteDeptById(deptno);
		}
		return 0;
	}

	@Override
	public int updateDept(Dept dept) {
		if(dept!=null){
			return deptDao.updateDept(dept);
		}
		return 0;
	}

	@Override
	public List<Dept> listDept() {
		return deptDao.listDept();
	}

	@Override
	public Dept findDeptById(Integer deptno) {
		System.out.println("service---------->"+deptno);
		return deptDao.findDeptById(deptno);
	}

	@Override
	public void listDeptByPager(Pager<Dept> pager) {
		deptDao.listDeptByPager(pager);
		long totalCount=deptDao.getTotalCount();
		pager.setTotalCount(totalCount);
	}

}
