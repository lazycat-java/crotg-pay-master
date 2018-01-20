package com.crotg.pay.service.user.facaed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.po.User;
import com.crotg.pay.facaed.service.UserFacaed;
import com.crotg.pay.service.user.service.UserService;

@Service("userFacaed")
public class UserFacaedImpl implements UserFacaed {
	
	@Autowired
	UserService userService;

	@Override
	public User getUserById(long id) {
		System.out.println("调用本机服务");
		// TODO Auto-generated method stub
		return userService.getUserById(id);
	}

}
