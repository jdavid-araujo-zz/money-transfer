package service;

import java.math.BigDecimal;
import java.util.List;

import exceptionhandler.exception.TransactionException;
import model.Account;
import model.Transaction;
import repository.impl.TransactionRepositoryImpl;
import util.Message;

public class TransactionService {

	private TransactionRepositoryImpl transactionRepository;
	private AccountService accountService;

	public TransactionService() {
		this.transactionRepository = new TransactionRepositoryImpl();
		this.accountService = new AccountService();
	}

	public void create(Transaction transaction) {

		this.validTransaction(transaction);
		
		this.transactionRepository.save(transaction);
	}

	public List<Transaction> findAllTransactionByFromAccount(Long fromAccountId) {
		return this.transactionRepository.findAllTransactionByFromAccount(fromAccountId);
	}

	public List<Transaction> findAllTransactionByToAccount(Long toAccountId) {
		return this.transactionRepository.findAllTransactionByToAccount(toAccountId);
	}

	public List<Transaction> findAllTransactionByFromAccountAndToAccount(Long fromAccountId, Long toAccountId) {
		return this.transactionRepository.findAllTransactionByFromAccountAndToAccount(fromAccountId, toAccountId);
	}
	
	public List<Transaction> findAll() {
		return this.transactionRepository.findAll();
	}


	public void validTransaction(Transaction transaction) {

		if (transaction.getFromAccount().equals(transaction.getToAccount())) {
			throw new TransactionException(Message.TRANSFERENCE_SAME_ACCOUNT_NOT_ALLOW);
		}

		if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransactionException(Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0);
		}

		Account fromAccount = this.accountService.findById(transaction.getFromAccount());

		Account toAccount = this.accountService.findById(transaction.getToAccount());

		BigDecimal newBalance = fromAccount.getBalance().subtract(transaction.getAmount());

		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new TransactionException(Message.AMMOUNT_INSUFFICIENT + ": " + transaction.getAmount());
		}

		try {
		
		this.accountService.update(fromAccount.getId(), newBalance);
		this.accountService.update(toAccount.getId(), toAccount.getBalance().add(transaction.getAmount()));
		
		} catch (Exception e) {
			this.accountService.update(fromAccount.getId(), fromAccount.getBalance());
			this.accountService.update(toAccount.getId(), toAccount.getBalance());
		}

	}
}
