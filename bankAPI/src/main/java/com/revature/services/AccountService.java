package com.revature.services;

import java.util.Set;

import com.revature.data.AccountDAO;
import com.revature.data.AccountStatusDAO;
import com.revature.data.AccountTypeDAO;
import com.revature.data.UserDAO;

import com.revature.humans.Account;
import com.revature.humans.AccountStatus;
import com.revature.humans.AccountType;
import com.revature.humans.User;
public class AccountService {
	private AccountDAO accountDao;
	private AccountStatusDAO accountStatusDao;
	private AccountTypeDAO accountTypeDao;
	private UserDAO userDao;
	
	public AccountService(AccountDAO ad, AccountStatusDAO asd, AccountTypeDAO atd, UserDAO ud) {
		accountDao = ad;
		accountStatusDao = asd;
		accountTypeDao = atd;
		userDao = ud;
	}
	
	public Integer addAccount(Account a) {
		return accountDao.createAccount(a);
	}
	
	public Account getAccountbyID(Integer id) {
		return accountDao.getAccountbyID(id);
	}
	
	public Set<Account> getAccountbyStatus(AccountStatus s){
		return accountDao.getAccountbyStatus(s);
	}
	
	public void updateAccount(Account a) {
		accountDao.updateAccount(a);
	}
	
	public void deleteAccount(Account a) {
		accountDao.deleteAccount(a);
	}
	
	
	public Set<Account> getAccountbyType(AccountType t){
		return accountDao.getAccountbyType(t);
	}
	
	public Set<AccountStatus> getAccountStatuses(){
		return accountStatusDao.getAccountStatuses();
	}
	public Set<AccountType> getAccountTypes(){
		return accountTypeDao.getAccountTypes();
	}
	
	public AccountType getAccountTypebyId(Integer id) {
		return accountTypeDao.getAccountTypebyID(id);
	}
	
	public AccountStatus getAccountStatusbyId(Integer id) {
		return accountStatusDao.getAccountStatusbyID(id);
	}
	
	public void ownAccount(User u, Account a) {
		AccountType at = accountTypeDao.getAccountTypebyType("Open");
		a.setAccountType(at);
		accountDao.updateAccount(a);
		
		Set<Account> accounts = u.getAccounts();
		accounts.add(a);
		u.setAccounts(accounts);
		userDao.updateUser(u);
		
	}

}
