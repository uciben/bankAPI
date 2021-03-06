package com.revature.data;

import java.util.Set;

import com.revature.humans.*;

public interface AccountDAO {
	public Integer createAccount(Account a);
	public Account getAccountbyID(Integer id);
	public Set<Account> getAccountbyStatus(AccountStatus status);
	public Set<Account> getAccountbyType(AccountType type);
	public void updateAccount(Account a);
	public void deleteAccount(Account a);
}
