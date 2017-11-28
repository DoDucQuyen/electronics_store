package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import entities.SubCategory;
public class SubCategoryDao extends DbManager{
	public HashMap<Integer, ArrayList<SubCategory>> getCategoryMap(){
		HashMap<Integer, ArrayList<SubCategory>> map = new HashMap<>();
		String sql = "select * from sub_category";
		PreparedStatement pstm;
		ResultSet rs;
		SubCategory subCategory;
		ArrayList<SubCategory> listSubCategory;
		
		openConnection();
		if(connection != null){
			try {
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()){
					subCategory = new SubCategory();
					subCategory.setId(rs.getInt("id"));
					subCategory.setName(rs.getString("name"));
					subCategory.setDescription(rs.getString("description"));
					subCategory.setCategoryId(rs.getInt("category_id"));
					
					listSubCategory = map.get(subCategory.getCategoryId());
					if(listSubCategory == null){
						listSubCategory = new ArrayList<>();
						listSubCategory.add(subCategory);
						map.put(subCategory.getCategoryId(), listSubCategory);
					}
					else {
						listSubCategory.add(subCategory);
						map.put(subCategory.getCategoryId(), listSubCategory);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return map;
	}
	
	public boolean save(Integer category_id, String name, String description) {
		boolean result = false;
		PreparedStatement pstm;
		StringBuilder sql = new StringBuilder("");

		openConnection();
		if (connection != null) {
		    try {
			sql.append("insert into sub_category (name, description, category_id) values(?,?,?)");
			pstm = connection.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, name);
			pstm.setString(++i, description);
			pstm.setInt(++i, category_id);
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
	
	public static void main(String []args) {
		SubCategoryDao dao = new SubCategoryDao();
		HashMap map = dao.getCategoryMap();
		ArrayList<SubCategory> list = (ArrayList) map.get(3);
		System.out.println(list);
		for(SubCategory sub : list){
			System.out.println(sub.getName());
		}
		
	}
}
