package com.hqhx.dao;

import java.util.List;

import com.hqhx.model.User;

public interface UserDao {

	/**
	 * 添加用户信息
	 * @param user：用户对象
	 * @return：返回整数 >0表示添加成功，否则表示添加失败
	 */
	public int addUser(User user);

	/**
	 * 删除用户
	 * @param id：要删除的用户编号
	 * @return：返回整数 >0表示删除成功，否则表示删除失败
	 */
	public int deleteUserById(Integer id);
	/**
	 * 修改用户信息
	 * @param user：新的用户信息
	 * @return：返回整数 >0表示修改成功，否则表示修改失败
	 */
	public int updateUser(User user);
	
	
	/**
	 * 查询所有用户信息
	 * @return：返回所有用户信息
	 */
	public List<User> listUser();
	
	/**
	 * 根据编号查询用户对象
	 * @param id：要查询的用户的编号
	 * @return:返回查询到的用户对象
	 */
	public User findUserById(Integer id);

	/**
	 * 根据用户名查询用户对象
	 * @param username：要查询的用户的编号
	 * @return:返回查询到的用户对象
	 */
	public User findUserByName(String username);
	

}
