package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRuleDao extends DbManager{
	public ArrayList<Integer> getRule(int product_id, int numberRule) {
		ArrayList<Integer> list = null;
		String sql = "select product_id2 from product_rule WHERE product_id1 = ? ORDER BY sup_count DESC LIMIT ?";
		PreparedStatement pstm;
		ResultSet rs;
		
		openConnection();
		if(connection != null) {
			try {
				list = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				pstm.setInt(1, product_id);
				pstm.setInt(2, numberRule);
				rs = pstm.executeQuery();
				while(rs.next()){
					list.add(rs.getInt("product_id2"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	} 
	
	public boolean saveRule(int product_id1, int product_id2, int supportCount, double conf) {
		boolean result = false;
		PreparedStatement pstm;
		StringBuilder sql = new StringBuilder("");

		openConnection();
		if (connection != null) {
		    try {
			sql.append("insert into product_rule (product_id1, product_id2, sup_count,conf) values(?,?,?,?) ");
			pstm = connection.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, product_id1);
			pstm.setInt(++i, product_id2);
			pstm.setInt(++i, supportCount);
			pstm.setDouble(++i, conf);
			pstm.executeUpdate();
			pstm.clearBatch();
			result = true;
		    } catch (Exception e) {
			e.printStackTrace();
		    } finally {
			closeConnection();
		    }
		}

		return result;
	}

	public boolean deleteAllRules() {
		boolean result = false;
		PreparedStatement pstm;
		String sqlTruncate;

		openConnection();
		if (connection != null) {
		    try {
			sqlTruncate = "truncate table product_rule";
			pstm = connection.prepareStatement(sqlTruncate);
			pstm.executeUpdate();
			result = true;
		    } catch (SQLException e) {
			e.printStackTrace();
		    } finally {
			closeConnection();
		    }
		}

		return result;
    }
	
	public static void main(String []agrs){
		ProductRuleDao dao = new ProductRuleDao();
		for(int i : dao.getRule(1, 4)){
			System.out.println(i);
		}
	}
}
