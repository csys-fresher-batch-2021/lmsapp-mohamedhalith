package in.mohamedhalith.exception;

public class ServiceException extends Exception{
	
	public ServiceException(Exception e , String message) {
		super(message,e);
	}
	
}