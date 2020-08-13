package com.revature.humans;

public class Role{
	private int roleId;
	private String role;
	
	public Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
	
	public Role() {
		roleId = 0;
		role = "";
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}