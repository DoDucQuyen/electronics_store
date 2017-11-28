package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Account;
import ultils.MD5Algorithm;


public class AccountDao extends DbManager{
	public boolean isExist(String username) {
		String sql = "select * from account where username = ?";
		PreparedStatement pstm;
		ResultSet rs;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, username);
				rs = pstm.executeQuery();
				while(rs.next()) {
					connection.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
	
	public boolean insertAccount(Account account) {
		String sql = "insert into account (username, password, address, phone, role) values(?, ?, N?, ?, ?)";
		PreparedStatement pstm;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				int index = 0;
				pstm.setString(++index, account.getUsername());
				pstm.setString(++index, account.getPassword());
				pstm.setString(++index, account.getAddress());
				pstm.setString(++index, account.getPhone());
				pstm.setInt(++index, account.getRole());
				pstm.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public Account getAccount(String username) {
		Account account = null;
		String sql = "select * from account where username = ?";
		PreparedStatement pstm;
		ResultSet rs;
		
		openConnection();
		if(connection != null) {
			try {
				account = new Account();
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, username);
				rs = pstm.executeQuery();
				while(rs.next()) {
					account.setId(rs.getInt("id"));
					account.setUsername(rs.getString("username"));
					account.setAddress(rs.getString("address"));
					account.setPhone(rs.getString("phone"));
					account.setRole(rs.getInt("role"));
					return account;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return account;
	}
	public Account checkAccount(String username, String password) {
		String sql = "select * from account where username = ? and password = ?";
		PreparedStatement pstm;
		ResultSet rs;
		Account account = null;
		
		openConnection();
		if(connection != null) {
			try {
				account = new Account();
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, username);
				pstm.setString(2, MD5Algorithm.encryption(password).toString());
				rs = pstm.executeQuery();
				while(rs.next()) {
					account.setId(rs.getInt("id"));
					account.setUsername(rs.getString("username"));
					account.setAddress(rs.getString("address"));
					account.setPhone(rs.getString("phone"));
					account.setRole(rs.getInt("role"));
					
					return account;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String []args){
		AccountDao dao = new AccountDao();
		System.out.println(dao.checkAccount("manh", "123456").getPhone());
		
	}
}
