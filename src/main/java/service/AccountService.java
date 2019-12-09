package service;

import java.math.BigDecimal;
import java.util.List;

import exceptionhandler.exception.AccountNotFoundException;
import exceptionhandler.exception.AmountMoneyException;
import model.Account;
import repository.impl.AccountRepositoryImpl;
import util.Message;

public class AccountService {	
	
	private AccountRepositoryImpl accountRepository;
	
	public AccountService() {
		this.accountRepository = new AccountRepositoryImpl();
	}

	public List<Account> findAll() {
		return this.accountRepository.findAll();
	}
	
	public Account findById(Long id ){
		Account account = this.accountRepository.findById(id);
		
		if (account == null) {
			throw new AccountNotFoundException(Message.ACCOUNT_NOT_EXIST + ": " + id);
		}
		
		return account;
	}
	
	public void create(Account account) {
		
		if(account.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
			throw new AmountMoneyException(Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0);
		}
		
		this.accountRepository.insertAccount(account);
	}

	public void update(Long id, BigDecimal balance) {
		this.accountRepository.update(id, balance);
	}

}