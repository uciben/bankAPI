package com.revature.services;

import java.util.Set;

import com.revature.data.AccountDAO;
import com.revature.data.RoleDAO;
import com.revature.data.UserDAO;
import com.revature.humans.Account;
import com.revature.humans.Role;
import com.revature.humans.User;

public class UserService {
	private UserDAO userDao;
	private RoleDAO roleDao;
	private AccountDAO accountDao;
	
	public UserService(UserDAO ud, RoleDAO rd, AccountDAO ad) {
		userDao = ud;
		roleDao = rd;
		accountDao = ad;
	}
	
	public Integer registerUser(User u) {
		return userDao.createUser(u);
	}
	
	public User logIn(String username, String password) {
		return userDao.getUserbyUsernameAndPassword(username, password);
	}
	
	public User findUserbyName(String username) {
		return userDao.getUserbyName(username);
	}
	
	public User findUserbyEmail(String email) {
		return userDao.getUserbyEmail(email);
	}
	
	public User findUserbyID(Integer id) {
		return userDao.getUserbyID(id);
	}
	
	public void updatePassword(User u, String password) {
		u.setPassword(password);
		userDao.updateUser(u);
	}
	
	public void updateEmail(User u, String email) {
		u.setEmail(email);
		userDao.updateUser(u);
	}
	

	public void deleteUser(User u) {
		Set<Account> accounts = u.getAccounts();
		for(Account a: accounts) {
			accountDao.deleteAccount(a);
		}
		userDao.deleteUser(u);
	}
	
	public Set<Role> getRoles() {
		return roleDao.getRoles();
	}
	
	public Set<User> getUserbyRole(Role r){
		return userDao.getUserbyRole(r);
	}
	
}
