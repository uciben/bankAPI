package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.humans.AccountStatus;
import com.revature.utils.ConnectionUtil;

public class AccountStatusPostGres implements AccountStatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Integer createAccountStatus(AccountStatus as) {
		Integer id = 0;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into account_status (default, ?)";
			String [] keys = {"status_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, as.getStatus());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt("status_id");
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
	public AccountStatus getAccountStatusbyID(Integer id) {
		// TODO Auto-generated method stub
		AccountStatus as = null;
		try(Connection conn = cu.getConnection()){
			String sql = "select * from account_status where status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				as = new AccountStatus(rs.getInt("status_id"), rs.getString("status_name"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return as;
	}

	@Override
	public AccountStatus getAccountStatusbyName(String name) {
		// TODO Auto-generated method stub
		AccountStatus as = null;
		try(Connection conn = cu.getConnection()){
			String sql = "select * from account_status where status_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				as = new AccountStatus(rs.getInt("status_id"), rs.getString("status_name"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return as;
	}
	
	public Set<AccountStatus> getAccountStatuses(){
		Set<AccountStatus> statuses = null;
		
		try(Connection conn = cu.getConnection()) {
			String sql = "Select * from account_status";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			statuses = new HashSet<>();
			while(rs.next()) {
				statuses.add(new AccountStatus(rs.getInt("status_id"), rs.getString("status_name")));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}
	@Override
	public void updateAccountStatus(AccountStatus as) {
		// TODO Auto-generated method stub
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update account_status set status_name = ? where status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, as.getStatus());
			pstmt.setInt(2, as.getStatusId());
			
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

	@Override
	public void deleteAccountStatus(AccountStatus as) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from account_status where status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, as.getStatusId());
			
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

}