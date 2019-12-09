package exceptionhandler.exception;

public class AccountNotHaveMoneyException extends RuntimeException {

	private static final long serialVersionUID = -308049108246869929L;

	public AccountNotHaveMoneyException(String errorMessage) {
        super(errorMessage);
    }
	
	public AccountNotHaveMoneyException() {
        super();
    }
}
