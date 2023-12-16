package validator;
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
	
	public boolean EmailValidator(String email) {
		String regex = "[\\w._%!-]+@[\\w.-]+\\.[a-zA-Z]{2,4}";
		
		 	Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(email);
		    return matcher.matches();


	}

}
