package in.mohamedhalith.exception;

public class ValidationException extends Exception{

//	public ValidationException(Exception e,String message) {
//		super(message,e);
//	}
	
	public ValidationException(String message) {
		super(message);
	}
}
