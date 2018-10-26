package com.partymasses.modules.dao.mybatis.entity;

import java.util.List;

public class OutputUnit {
	List<Unit>listunit;
	int number;
	public OutputUnit(List<Unit> listunit, int number) {
		super();
		this.listunit = listunit;
		this.number = number;
	}
	public OutputUnit() {
		super();
	}
	public List<Unit> getListunit() {
		return listunit;
	}
	public void setListunit(List<Unit> listunit) {
		this.listunit = listunit;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
