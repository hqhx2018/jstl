package com.hqhx.dao.impl;

import java.util.List;

import com.hqhx.dao.UserDao;
import com.hqhx.model.User;
import com.hqhx.util.DBHelper;

public class UserDaoImpl implements UserDao{

	private DBHelper db=new DBHelper();
	
	@Override
	public int addUser(User user) {
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

	@Override
	public User findUserByName(String username) {
		String sql="select id,username,password from tuser where username=?";
		List<User> users=db.query(sql, User.class, username);
		if(users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}

}
