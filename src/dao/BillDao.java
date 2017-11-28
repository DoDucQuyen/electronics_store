package dao;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.Bill;
import entities.Cart;
import entities.Category;

public class BillDao extends DbManager{
	public void insertBill(Bill bill) {
		String sql = "insert into bill (cart_detail, address, phone, account_id, checked) values(?, N?, ?, ? , ?)";
		PreparedStatement pstm;
		
		openConnection();
		if(connection != null) {
			try {
				pstm = connection.prepareStatement(sql);
				int index = 0;
				pstm.setString(++index, bill.getCart().toString());
				pstm.setString(++index, bill.getAddress());
				pstm.setString(++index, bill.getPhone());
				pstm.setInt(++index, bill.getAccountId());
				pstm.setInt(++index, 0);
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public List<String> getListCartDetail(){
		List<String> listCart = null;
		String sql = "select cart_detail from bill";
		PreparedStatement pstm;
		ResultSet rs;
		openConnection();
		if(connection != null){
			try {
				listCart = new ArrayList<String>();
				pstm = connection.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()){
					listCart.add(rs.getString("cart_detail"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return listCart;
	}
	
	public static void main(String []args) {
		BillDao dao = new BillDao();
//		Bill bill = new Bill();
//		bill.setCart(new Cart());
//		bill.setAddress("Kiến Xương");
//		bill.setPhone("092812391");
//		bill.setAccountId(1);
//		dao.insertBill(bill);
		dao.getListCartDetail();
	}
}
