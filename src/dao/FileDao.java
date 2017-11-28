package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileDao {

	private static PrintWriter createFile(String url) {
		
		if (url == null) return null;
		File file = new File(url);
		PrintWriter out = null;

		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
	public boolean writeListCartToFile(String url1, String url2) {
		
		boolean result = false;
		BillDao billDao = new BillDao();
		JSONObject cartDetail, jsonObject;
		JSONArray jsonArray;
		JSONParser jsonParser = new JSONParser();
		List<String> listCart = billDao.getListCartDetail();
		PrintWriter out1 = null, out2 = null;
		try {
			out1 = createFile(url1);
			out2 = createFile(url2);
		    if (out1 == null || out2 == null) return false;
		    
		    ProductDao product = new ProductDao();
			HashMap<Integer, Integer> productMap = product.getProductMap();
		    
			for (String cart : listCart) {
		    	jsonArray = (JSONArray) jsonParser.parse(cart);
				StringBuilder line1 = new StringBuilder("");
				StringBuilder line2 = new StringBuilder("");
				
				for (int i = 0; i < jsonArray.size(); i++) {
				    jsonObject = (JSONObject) jsonArray.get(i);
				    Set<String> keys = jsonObject.keySet();
				    
				    for (String key : keys) {
				    	line1.append(key + " ");
				    	Integer subCateId = productMap.get(Integer.parseInt(key));				    	
				    	line2.append(subCateId + " ");
				    }
				}
				
				if (jsonArray.size() > 0) {
					out1.println(line1.toString());
					out2.println(line2.toString());
				}
		    }
		    result = true;
		} catch (ParseException e) {
		    e.printStackTrace();
		} finally {
		    out1.close();
		    out2.close();
		}

		return result;
	}
	
	public static void main(String[] args) {
		FileDao dao = new FileDao();
		dao.writeListCartToFile("Product", "SubCategory");
	}
}
