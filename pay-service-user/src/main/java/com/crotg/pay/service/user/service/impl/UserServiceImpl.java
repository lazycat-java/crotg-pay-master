package com.crotg.pay.service.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.po.User;
import com.crotg.pay.service.user.dao.UserDao;
import com.crotg.pay.service.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

}
