package com.revature.data;

import java.util.*;
import com.revature.humans.Role;
import com.revature.humans.User;

public interface UserDAO {
	public Integer createUser(User u);
	public User getUserbyID(int id);
	public User getUserbyEmail(String email);
	public User getUserbyName(String name);
	public User getUserbyUsernameAndPassword(String username, String password);
	public Set<User> getUserbyRole(Role r);
	public void updateUser(User u);
	public void deleteUser(User u);
}
