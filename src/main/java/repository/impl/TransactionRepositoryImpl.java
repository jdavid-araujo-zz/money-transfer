package repository.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import config.JDBIConfig;
import model.Transaction;
import repository.TransactionRepository;

public class TransactionRepositoryImpl {

	private Jdbi jdbi = JDBIConfig.getJdbi();

	public Long save(Transaction transaction) {
		return jdbi.withExtension(TransactionRepository.class, 
				dao -> dao.save(transaction));
	}
	
	public List<Transaction> findAllTransactionByFromAccount(Long fromAccountId) {
		return jdbi.withExtension(TransactionRepository.class, 
				dao -> dao.findAllTransactionByFromAccount(fromAccountId));
	}
	
	public List<Transaction> findAllTransactionByToAccount(Long toAccountId) {
		return jdbi.withExtension(TransactionRepository.class, 
				dao -> dao.findAllTransactionByToAccount(toAccountId));
	}
	
	public List<Transaction> findAllTransactionByFromAccountAndToAccount(Long fromAccountId, Long toAccountId) {
		return jdbi.withExtension(TransactionRepository.class, 
				dao -> dao.findAllTransactionByFromAccountAndToAccount(fromAccountId, toAccountId));
	}
	
	public List<Transaction> findAll() {
		return jdbi.withExtension(TransactionRepository.class, 
				dao -> dao.findAll());
	}
}
