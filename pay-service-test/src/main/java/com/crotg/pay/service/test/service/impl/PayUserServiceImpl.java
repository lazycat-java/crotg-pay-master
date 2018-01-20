package com.crotg.pay.service.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.test.po.PayUser;
import com.crotg.pay.service.test.dao.PayUserDao;
import com.crotg.pay.service.test.service.PayUserService;

@Service
public class PayUserServiceImpl implements PayUserService {
	
	@Autowired
	PayUserDao payUserDao;
	
	@Override
	public PayUser selectOne(long id) {
		return payUserDao.selectOne(id);
	};

}
