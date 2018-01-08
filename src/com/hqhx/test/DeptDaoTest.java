package com.hqhx.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.hqhx.dao.DeptDao;
import com.hqhx.dao.impl.DeptDaoImpl;
import com.hqhx.model.Dept;

public class DeptDaoTest {

	DeptDao deptDao=new DeptDaoImpl();
	
	@Test
	public void testAddDept() {
		Dept d=new Dept(10,"aa","bb");
		int i=deptDao.addDept(d);
		System.out.println(i);
	}

	@Test
	public void testDeleteDeptById() {
		int i=deptDao.deleteDeptById(10);
		System.out.println(i);
	}

	@Test
	public void testUpdateDept() {
		Dept d=new Dept(10,"²âÊÔ²¿","Î÷°²");
		int i=deptDao.updateDept(d);
		System.out.println(i);
	}

	@Test
	public void testListDept() {
		List<Dept> depts=deptDao.listDept();
		for (Dept dept : depts) {
			System.out.println(dept);
		}
	}

	@Test
	public void testFindDeptById() {
		Dept dept=deptDao.findDeptById(20);
		System.out.println(dept);
	}

}
