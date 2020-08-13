package com.revature.data;

import java.util.Set;

import com.revature.humans.AccountType;
public interface AccountTypeDAO {
	public Integer createAccountType(AccountType type);
	public AccountType getAccountTypebyID(Integer id);
	public AccountType getAccountTypebyType(String type);
	public Set<AccountType> getAccountTypes();
	public void updateAccountType(AccountType type);
	public void deleteAccountType(AccountType type);

}
