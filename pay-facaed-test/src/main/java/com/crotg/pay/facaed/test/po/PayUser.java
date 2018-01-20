package com.crotg.pay.facaed.test.po;

import java.io.Serializable;

public class PayUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
