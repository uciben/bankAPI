package com.revature.data;

import com.revature.humans.AccountType;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.sql.PreparedStatement;

public class AccountTypePostGres implements AccountTypeDAO {
	private ConnectionUtil cu =  ConnectionUtil.getConnectionUtil();
	
	@Override
	public Integer createAccountType(AccountType type) {
		Integer id = 0;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into account_type values (default, ?)";
			String [] keys = {"type_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, type.getType());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt("type_id");
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

	@Override
	public AccountType getAccountTypebyID(Integer id) {
		AccountType at = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from account_type where type_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				at = new AccountType(rs.getInt("type_id"), rs.getString("type_name"));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return at;
	}

	@Override
	public AccountType getAccountTypebyType(String type) {
		AccountType at = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from account_type where type_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				at = new AccountType(rs.getInt("type_id"), rs.getString("type_name"));
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return at;
	}
	public Set<AccountType> getAccountTypes(){
		Set<AccountType> types = null;
		
		try(Connection conn = cu.getConnection()) {
			String sql = "Select * from account_type";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			types = new HashSet<>();
			while(rs.next()) {
				types.add(new AccountType(rs.getInt("type_id"), rs.getString("type_name")));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return types;
	}
	@Override
	public void updateAccountType(AccountType type) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update account_type set type_name = ? where type_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(2, type.getTypeId());
			stmt.setString(1,  type.getType());
			
			int rowsAffected = stmt.executeUpdate();
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

	@Override
	public void deleteAccountType(AccountType type) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from account_type where type_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, type.getTypeId());
			
			int rowsAffected = stmt.executeUpdate();
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

}
