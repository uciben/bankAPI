package com.revature.humans;

public class AccountType {
	private int typeId;
	private String type;
	
	public AccountType(int typeId, String type) {
		this.typeId = typeId;
		this.type = type;
	}
	
	public AccountType() {
		this.typeId = 0;
		this.type = "";
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public int getTypeId() {
		return this.typeId;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}