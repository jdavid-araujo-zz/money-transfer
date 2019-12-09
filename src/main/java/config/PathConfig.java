package config;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import exceptionhandler.Error;
import exceptionhandler.ErrorMessage;
import exceptionhandler.ExceptionHandler;
import exceptionhandler.exception.AccountNotFoundException;
import exceptionhandler.exception.AmountMoneyException;
import exceptionhandler.exception.TransactionException;
import resource.AccountResource;
import resource.TransactionResource;
import spark.Spark;
import util.JsonUtil;
import util.Message;

public class PathConfig {

	static public void init() {
		
		AccountResource accountResource = new AccountResource();
		TransactionResource transactionResource = new TransactionResource();
		
		
		Spark.port(8080);
		
		Spark.path("/api/", () -> {
			Spark.path("v1/", () -> {
				Spark.path("accounts", () -> {
					Spark.get("", (req, res) -> {return accountResource.findAll(req, res);}, JsonUtil.json());
					Spark.get("/:id", (req, res) -> {return accountResource.findById(req, res);}, JsonUtil.json());
					Spark.post("", (req, res) -> {return accountResource.save(req, res);}, JsonUtil.json());	
					
					Spark.get("/:toAccountId/transferences", (req, res) -> {return transactionResource.findAllTransactionByAccount(req, res);}, JsonUtil.json());
				});
				Spark.path("transferences", () -> {
					Spark.get("", (req, res) -> {return transactionResource.findAll(req, res);}, JsonUtil.json());		
				});
				Spark.path("transferences", () -> {
					Spark.post("", (req, res) -> {return transactionResource.save(req, res);}, JsonUtil.json());		
				});
			});
		});
		
		
		Spark.exception(TransactionException.class, 
				(exception, req, res) -> {ExceptionHandler.transactionException(exception, req, res);});
		
		Spark.exception(AccountNotFoundException.class, 
				(exception, req, res) -> {ExceptionHandler.accountNotFoundException(exception, req, res);});
		
		Spark.exception(AmountMoneyException.class, 
				(exception, req, res) -> {ExceptionHandler.amountMoneyException(exception, req, res);});
		
		Spark.exception(Exception.class, 
				(exception, req, res) -> {ExceptionHandler.defaultException(exception, req, res);});
		
		
		Spark.notFound((req, res) -> {
		    res.type("application/json");
		    res.status(HttpServletResponse.SC_NOT_FOUND);

		    ErrorMessage errorMessage = new ErrorMessage(Message.RESOURCE_NOT_FOUND, Message.RESOURCE_NOT_FOUND);
			Error error = new Error("NotFoundExepction", HttpServletResponse.SC_NOT_FOUND, new Date().getTime(), errorMessage);
		    
		    return error;
		});
		
	}
	
}
