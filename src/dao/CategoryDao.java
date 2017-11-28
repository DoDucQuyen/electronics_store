package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entities.Category;

public class CategoryDao extends DbManager{
	public List<Category> getListCategory(){
		List listCategory = null;
		Category category;
		String sql = "select * from category";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null){
			try {
				listCategory = new ArrayList<>();
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()){
					category = new Category();
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("name"));
					category.setDescription(rs.getString("description"));
					listCategory.add(category);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return listCategory;
	}
	
	public boolean save(String name, String description) {
		boolean result = false;
		PreparedStatement pstm;
		StringBuilder sql = new StringBuilder("");

		openConnection();
		if (connection != null) {
		    try {
			sql.append("insert into category (name, description) values(?,?)");
			pstm = connection.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, name);
			pstm.setString(++i, description);
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
	
	public static void main(String []agrs){
		CategoryDao dao = new CategoryDao();
		System.out.print(dao.getListCategory());
	}
}
