package com.crotg.pay.service.test.facaed;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.test.facaed.PayUserFacaed;
import com.crotg.pay.facaed.test.po.PayUser;
import com.crotg.pay.service.test.service.PayUserService;

@Path("users")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class PayUserFacaedImpl implements PayUserFacaed {
	
	@Autowired
	PayUserService payUserService;

	@Override
	public PayUser selectOne(long id) {
		// TODO Auto-generated method stub
		return payUserService.selectOne(id);
	}

}
