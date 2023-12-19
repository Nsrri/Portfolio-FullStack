package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import validator.InputValidator;

import model.Viewer;
import service.ViewerService;

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
  
  
  public boolean userExists(String email, int id) {
	  boolean isRelatedUser = false; 
	  InputValidator validator = new InputValidator();
	  ViewerService viewService  = new ViewerService();

		Viewer viewer = null;
		// Check if the email address is valid and if the email of retrieved user is the same as client email
      if(validator.emailValidator(email)) {
      	viewer =  viewService.getById(id);
      	if(viewer.getEmail() == email) {
      		isRelatedUser = true;
      	}

}
	return isRelatedUser;
  }
}
