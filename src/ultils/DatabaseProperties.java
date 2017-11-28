package ultils;

import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
	private static Properties properties= null;
	
	//load properties
	private static void loadProperties(){
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
			        .getResourceAsStream(Constant.DATABASE_PROPERTIES_FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//getProperty
	public static String getProperty(String key){
		if(properties == null){
			loadProperties();
		}
		return (String)properties.get(key);
	}
	
	public static void main(String []args){
		System.out.println(DatabaseProperties.getProperty("username"));
	}
}
