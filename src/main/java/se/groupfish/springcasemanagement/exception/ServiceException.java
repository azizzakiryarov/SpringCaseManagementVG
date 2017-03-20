package se.groupfish.springcasemanagement.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 7678937278313456283L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

}
