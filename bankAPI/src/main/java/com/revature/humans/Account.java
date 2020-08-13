package com.revature.humans;
public class Account {
	private int accountId;
	private double balance;
	private AccountStatus status;
	private AccountType type;
	
	public Account(int accountId, double balance, AccountStatus status, AccountType type) {
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	
	public Account() {
		this.accountId = 0;
		this.balance = 0;
		this.status = new AccountStatus();
		this.type = new AccountType();
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public int getAccountId() {
		return this.accountId;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setAccountStatus(AccountStatus status) {
		this.status = status;
	}
	
	public AccountStatus getAccountStatus() {
		return this.status;
	}
	
	public void setAccountType(AccountType type){
		this.type = type;
	}
	
	public AccountType getAccountType() {
		return this.type;
	}
}