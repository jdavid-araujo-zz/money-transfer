package exceptionhandler.exception;

public class AmountMoneyException extends RuntimeException {

	private static final long serialVersionUID = 212360808537066343L;

	public AmountMoneyException(String errorMessage) {
        super(errorMessage);
    }
	
	public AmountMoneyException() {
        super();
    }
}
