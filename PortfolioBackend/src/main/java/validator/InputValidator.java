package validator;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	public boolean passwordValidator(String password) {
		String regex = "^[A-Z][a-zA-Z0-9!@#$%^&*()_+{}|:\"<>?`\\\\\\-=[\\\\];',.\\\\\\\\]{7,}$";
;
;
		
		 	Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(password);
		    return matcher.matches();


	}
	
	public boolean emailValidator(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		 	Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(email);
		    return matcher.matches();
	}
	
	
	public <T> boolean notNullValidator(T inputObject) {
		return inputObject == null ? false : true;
		
	}
	
	public boolean validLength(String inputObject) {
		return inputObject.length() < 3 ? false : true;
		
	}
	
	public  boolean validRange(LocalDate inputObject, LocalDate lowest, LocalDate highest) {
		return inputObject.isAfter(lowest) &&  inputObject.isBefore(highest == null ? LocalDate.now() : highest) ? true : false;
	}
	
	
	

}
