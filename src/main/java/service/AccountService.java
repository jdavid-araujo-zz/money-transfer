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

	/**
	 * Return all Accounts
	 * 
	 * @return A list of Account
	 */
	public List<Account> findAll() {
		return this.accountRepository.findAll();
	}

	/**
	 * Return Account by Id
	 * 
	 * @param id
	 * @return A Single Account
	 */
	public Account findById(Long id) {
		Account account = this.accountRepository.findById(id);

		if (account == null) {
			throw new AccountNotFoundException(Message.ACCOUNT_NOT_EXIST + ": " + id);
		}

		return account;
	}

	/**
	 * Create a new Account
	 * 
	 * @param account
	 * @return the id of the new Account
	 */
	public long create(Account account) {

		if (account.getBalance().compareTo(BigDecimal.ZERO) <= 0) {
			throw new AmountMoneyException(Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0);
		}

		return this.accountRepository.insertAccount(account);
	}

	/**
	 * Update a Account
	 * 
	 * @param id      of the Account
	 * @param balance new value for the Account's balance
	 */
	public void update(Long id, BigDecimal balance) {
		this.accountRepository.update(id, balance);
	}

}