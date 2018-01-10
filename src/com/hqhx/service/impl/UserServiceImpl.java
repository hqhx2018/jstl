package com.hqhx.service.impl;

import java.util.List;

import com.hqhx.dao.UserDao;
import com.hqhx.dao.impl.UserDaoImpl;
import com.hqhx.model.User;
import com.hqhx.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public User login(String username) {
		User user=null;
		if(username!=null){
			user=userDao.findUserByName(username);
		}
		return user;
	}

	@Override
	public int reg(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
