package exceptionhandler.exception;

public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 1091649755753280793L;

	public TransactionException(String errorMessage) {
        super(errorMessage);
    }
	
	public TransactionException() {
        super();
    }
}