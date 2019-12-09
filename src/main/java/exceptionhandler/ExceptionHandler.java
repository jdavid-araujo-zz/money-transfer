package exceptionhandler;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import exceptionhandler.exception.AccountNotFoundException;
import exceptionhandler.exception.AmountMoneyException;
import exceptionhandler.exception.TransactionException;
import spark.Request;
import spark.Response;
import util.JsonUtil;
import util.Message;

public class ExceptionHandler {
	
	public static void transactionException(TransactionException exception, Request request,
			Response response) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getMessage());
		Error error = new Error(TransactionException.class.getSimpleName(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Date().getTime(), errorMessage);
		
		response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.body(JsonUtil.toJson(error));
	}

	public static void accountNotFoundException(AccountNotFoundException exception, Request request,
			Response response) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getMessage());
		Error error = new Error(exception.getClass().getSimpleName(), HttpServletResponse.SC_NOT_FOUND, new Date().getTime(), errorMessage);
		
		response.status(HttpServletResponse.SC_NOT_FOUND);
		response.body(JsonUtil.toJson(error));
	}
	
	public static void amountMoneyException(AmountMoneyException exception, Request request,
			Response response) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getMessage());
		Error error = new Error(exception.getClass().getSimpleName(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Date().getTime(), errorMessage);
		
		response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.body(JsonUtil.toJson(error));
	}
	
	
	public static void  defaultException(Exception exception, Request request,
			Response response) {
		
		ErrorMessage errorMessage = new ErrorMessage(Message.UNABLE_PROCESS_OPERATION, (exception.getCause() == null ? exception.getMessage(): exception.getCause().getMessage()));
		Error error = new Error(exception.getClass().getSimpleName(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, new Date().getTime(), errorMessage);
		
		response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.body(JsonUtil.toJson(error));
	}
}
