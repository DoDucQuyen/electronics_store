package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubCateRuleDao extends DbManager{

	public boolean saveRule(int sub_category_id1, int sub_category_id2, int supportCount, double conf) {
		boolean result = false;
		PreparedStatement pstm;
		StringBuilder sql = new StringBuilder("");

		openConnection();
		if (connection != null) {
		    try {
			sql.append("insert into sub_category_rule (sub_category_id1, sub_category_id2, sup_count,conf) values(?,?,?,?) ");
			pstm = connection.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, sub_category_id1);
			pstm.setInt(++i, sub_category_id2);
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
			sqlTruncate = "truncate table sub_category_rule";
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
	
}
