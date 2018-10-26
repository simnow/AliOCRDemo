package com.partymasses.modules.dao.mybatis.entity;

import org.springframework.stereotype.Component;

@Component
public class InputUnitPut extends InputUnit {
	private int id;
	
	public InputUnitPut() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
