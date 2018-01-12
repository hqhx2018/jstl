package com.hqhx.dao;

import java.util.List;

import com.hqhx.model.Dept;
import com.hqhx.model.Pager;

public interface DeptDao {

	/**
	 * 添加部门信息
	 * @param dept：部门对象
	 * @return：返回整数 >0表示添加成功，否则表示添加失败
	 */
	public int addDept(Dept dept);

	/**
	 * 删除部门
	 * @param deptno：要删除的部门编号
	 * @return：返回整数 >0表示删除成功，否则表示删除失败
	 */
	public int deleteDeptById(Integer deptno);
	/**
	 * 修改部门信息
	 * @param dept：新的部门信息
	 * @return：返回整数 >0表示修改成功，否则表示修改失败
	 */
	public int updateDept(Dept dept);
	
	
	/**
	 * 查询所有部门信息
	 * @return：返回所有部门信息
	 */
	public List<Dept> listDept();
	
	
	/**
	 * 分页查询所有部门信息
	 * @return：
	 */
	public void listDeptByPager(Pager<Dept> pager);
	
	/**
	 * 根据编号查询部门对象
	 * @param deptno：要查询的部门的编号
	 * @return:返回查询到的部门对象
	 */
	public Dept findDeptById(Integer deptno);
	
	/**
	 * 获取总部门个数
	 * @return
	 */
	public Long getTotalCount();
}
