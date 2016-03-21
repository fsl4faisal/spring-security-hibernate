package com.faisal.users.dao;

import com.faisal.users.model.User;

public interface UserDao {
	
	User findByUserName(String username);

}
