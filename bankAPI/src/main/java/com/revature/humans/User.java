package com.revature.humans;

import java.util.Set;
import java.util.HashSet;
public class User{
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private Set<Account> accounts;


	public User(int useriD, String username, String password, String firstName, String lastName, String email, Role role){
		this.userID = useriD;
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		accounts = new HashSet<Account>();
	}
	
	public User() {
		this.userID = 0;
		this.password = "";
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.role = new Role();
		accounts = new HashSet<>();
	}
	
	public Integer getId() {
		return this.userID;
	}
	
	public void setID(Integer id) {
		this.userID = id;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole(Role r) {
		this.role = r;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public Set<Account> getAccounts(){
		return accounts;
	}
	
	public void setAccounts(Set<Account> a) {
		accounts = a;
	}
}