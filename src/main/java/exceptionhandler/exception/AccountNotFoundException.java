package exceptionhandler.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9062073432130682118L; 

	public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
	
	public AccountNotFoundException() {
        super();
    }
}
