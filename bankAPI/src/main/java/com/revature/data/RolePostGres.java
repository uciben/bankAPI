package com.revature.data;

import java.util.Set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import com.revature.utils.ConnectionUtil;
import com.revature.humans.Role;

public class RolePostGres implements RoleDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Integer createRole(Role r) {
		Integer id = 0;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into user_values values (default, ?)";
			String[] keys = {"role_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, r.getRole());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt(1);
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}

	public Role getRolebyId(Integer id) {
		// TODO Auto-generated method stub
		Role r = null;
		try(Connection conn = cu.getConnection()){
			String sql = "select user_id, user_name from user_role where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				r = new Role(rs.getInt("role_id"), rs.getString("role_name"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public Set<Role> getRoles() {
		// TODO Auto-generated method stub
		Set<Role> roles = null;
		try(Connection conn = cu.getConnection()){
			String sql = "select * from user_role";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			roles = new HashSet<>();
			while(rs.next()) {
				roles.add(new Role(rs.getInt("role_id"), rs.getString("role_name")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	public void updateRole(Role r) {
		// TODO Auto-generated method stub
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update user_role set role_name = ? where role_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getRole());
			pstmt.setInt(2,  r.getRoleId());
			
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteRole(Role r) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from user_role where role_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getRoleId());
			
			int rowsAffected = pstmt.executeUpdate();
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
