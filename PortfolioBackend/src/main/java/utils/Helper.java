package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Helper {
  
	public Helper() {
		
	}

  public String readProperties(String property) {
	 
	   
	  String url = "";
	   try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties") ){
      Properties prop = new Properties();
      // load a properties file
      prop.load(input);
      // get the property value and print it out
      url = prop.getProperty(property);
 
	   } catch (IOException ex) {
		   ex.printStackTrace();;
	   }
	   return url;
	   }

}
