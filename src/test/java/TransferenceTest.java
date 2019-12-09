import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import exceptionhandler.Error;
import model.Transaction;
import spark.Spark;
import util.Message;


public class TransferenceTest {

	@BeforeClass
    public static void beforeClass() {
        Main.main(null);
        Spark.awaitInitialization();
    }

    @AfterClass
    public static void afterClass() throws InterruptedException {
        Spark.stop();
        Thread.sleep(2000);
    }
    
    
    public Transaction mockTransference() {
    	return new Transaction(1001L, 1002L, new BigDecimal(25));
    }
    
    public Transaction mockTransferenceSameAccount() {
    	return new Transaction(1001L, 1001L, new BigDecimal(25));
    }
    
    public Transaction mockAmountNegative() {
    	return new Transaction(1001L, 1002L, new BigDecimal(-100));
    }
    
    public Transaction mockAmountBalanceInsufficient() {
    	return new Transaction(1001L, 1002L, new BigDecimal(10000));
    }
    
    public Transaction mockTransferenceAccountNotExist() {
    	return new Transaction(200L, 1002L, new BigDecimal(10));
    }
    
    
    
    @Test
    public void findAllTransferences() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .asString();
        
        Transaction transfers[] =
                 new Gson().fromJson(response.getBody(), Transaction[].class);
        
        assertEquals(response.getStatus(), HttpStatus.SC_OK);
        assertEquals(Long.valueOf(transfers[0].getFromAccount()), Long.valueOf(1001));
        assertEquals(Long.valueOf(transfers[0].getToAccount()), Long.valueOf(1002));
        assertEquals(transfers[0].getAmount(), BigDecimal.valueOf(30.35));
        assertEquals(Long.valueOf(transfers[1].getFromAccount()), Long.valueOf(1002));
        assertEquals(Long.valueOf(transfers[1].getToAccount()), Long.valueOf(1001));
        assertEquals(transfers[1].getAmount(), new BigDecimal("70.00"));

    }
    
    @Test
    public void makeTransference() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockTransference()))
                .asString();
        
        assertEquals(response.getStatus(),HttpStatus.SC_CREATED);
    }
    
    
    @Test
    public void makeTransferenceSameAccount() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockTransferenceSameAccount()))
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);      
        assertEquals(response.getStatus(),HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.TRANSFERENCE_SAME_ACCOUNT_NOT_ALLOW); 
    }
    
    @Test
    public void makeTransferenceAccountNotExist() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockTransferenceAccountNotExist()))
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);      
        assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.ACCOUNT_NOT_EXIST + ": " + 200); 
    }
    
    @Test
    public void makeTransferenceAmountNegative() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockAmountNegative()))
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);      
        assertEquals(response.getStatus(),HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0); 
    }
    
    @Test
    public void makeTransferenceAmountBalanceInsufficient() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/v1/transferences")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockAmountBalanceInsufficient()))
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);      
        assertEquals(response.getStatus(),HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.AMMOUNT_INSUFFICIENT + ": " + 10000); 
    }
}
