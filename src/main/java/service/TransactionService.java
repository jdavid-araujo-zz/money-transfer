package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	/**
	 * Create a new Transaction
	 * 
	 * @param transaction the object to the persisted
	 * @return the id of the new Transaction
	 */
	public Long create(Transaction transaction) {

		this.validTransaction(transaction);

		return this.transactionRepository.save(transaction);
	}

	/**
	 * Return a list of Transaction that sended a amount of money
	 * 
	 * @param fromAccountId the id that sended
	 * @return the list of Transaction
	 */
	public List<Transaction> findAllTransactionByFromAccount(Long fromAccountId) {
		return this.transactionRepository.findAllTransactionByFromAccount(fromAccountId);
	}

	/**
	 * Return a list of Transaction that received a amount of money
	 * 
	 * @param toAccountId the id that received
	 * @return the list of Transaction
	 */
	public List<Transaction> findAllTransactionByToAccount(Long toAccountId) {
		return this.transactionRepository.findAllTransactionByToAccount(toAccountId);
	}

	/**
	 * Return a list of Transaction
	 * 
	 * @param fromAccountId the id that sended
	 * @param toAccountId   the id that received
	 * @return the list of Transaction
	 */
	public List<Transaction> findAllTransactionByFromAccountAndToAccount(Long fromAccountId, Long toAccountId) {
		return this.transactionRepository.findAllTransactionByFromAccountAndToAccount(fromAccountId, toAccountId);
	}

	/**
	 * Return all Transaction
	 * 
	 * @return the list of Transaction
	 */
	public List<Transaction> findAll() {
		return this.transactionRepository.findAll();
	}

	/**
	 * Check if the transaction is valid
	 * 
	 * @param transaction the object to the verifed
	 */
	public synchronized void validTransaction(Transaction transaction) {

		if (transaction.getFromAccount().equals(transaction.getToAccount())) {
			throw new TransactionException(Message.TRANSFERENCE_SAME_ACCOUNT_NOT_ALLOW);
		}

		if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new TransactionException(Message.AMOUT_MONEY_TRANSFER_MUST_MORE_0);
		}

		Account fromAccount = this.accountService.findById(transaction.getFromAccount());

		Account toAccount = this.accountService.findById(transaction.getToAccount());

		transaction.setAmount(transaction.getAmount().setScale(2, RoundingMode.DOWN));
		BigDecimal newBalance = fromAccount.getBalance().subtract(transaction.getAmount());

		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new TransactionException(Message.AMMOUNT_INSUFFICIENT);
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
