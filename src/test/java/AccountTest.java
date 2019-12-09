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
import model.Account;
import spark.Spark;
import util.Message;

public class AccountTest {

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
    
    public Account mockNewAccount() {
    	return new Account("Carlos",  BigDecimal.valueOf(2500.43));
    }
    
    public Account mockNewAccountBalanceInvalid() {
    	return new Account("Carlos",  BigDecimal.valueOf(-10));
    }
    
	@Test
	public void createNewAccoundSucess() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:4567/api/v1/accounts")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockNewAccount()))
                .asString();
        
        assertEquals(response.getStatus(),HttpStatus.SC_CREATED);
	}
	
	@Test
	public void createNewAccoundBalanceInvalid() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:4567/api/v1/accounts")
                .header("accept", "application/json")
                .body(new Gson().toJson(mockNewAccountBalanceInvalid()))
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);
        assertEquals(response.getStatus(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0); 
	}
	
	@Test
	public void findAccount() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://localhost:4567/api/v1/accounts/1000")
                .header("accept", "application/json")
                .asString();
        
        Account account = new Gson().fromJson(response.getBody(), Account.class);
        assertEquals(response.getStatus(), HttpStatus.SC_OK);
        assertEquals((long) account.getId(), 1000L);
        assertEquals(account.getOwner(),"Carlos");
        assertEquals(account.getBalance(), BigDecimal.valueOf(2000.21));

	}
	
	@Test
	public void findAccoundNotExist() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://localhost:4567/api/v1/accounts/2000")
                .header("accept", "application/json")
                .asString();
        
        Error error = new Gson().fromJson(response.getBody(), Error.class);
        assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND);
        assertEquals(error.getErrorMessage().getMessageUser(), Message.ACCOUNT_NOT_EXIST + ": " + 2000);    

	}	
	
    @Test
    public void findAllAccounts() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://localhost:4567/api/v1/accounts")
                .header("accept", "application/json")
                .asString();
        
        Account account[] =
        		new Gson().fromJson(response.getBody(), Account[].class);
        
        
        assertEquals(response.getStatus(), HttpStatus.SC_OK);
        assertEquals(Long.valueOf(account[0].getId()), Long.valueOf(1000)); 
        assertEquals(Long.valueOf(account[1].getId()), Long.valueOf(1001));      
        assertEquals(Long.valueOf(account[2].getId()), Long.valueOf(1002));      
    }
}
