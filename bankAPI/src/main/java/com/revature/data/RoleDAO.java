package com.revature.data;

import java.util.Set;

import com.revature.humans.Role;

public interface RoleDAO {
	public Integer createRole(Role r);
	public Role getRolebyId(Integer id);
	public Set<Role> getRoles();
	public void updateRole(Role r);
	public void deleteRole(Role r);
	
}
