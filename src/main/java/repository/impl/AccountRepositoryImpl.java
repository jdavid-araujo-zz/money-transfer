package repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import config.JDBIConfig;
import model.Account;
import repository.AccountRepository;

public class AccountRepositoryImpl {

    private Jdbi jdbi = JDBIConfig.getJdbi();
    
    public void insertAccount(Account account) {
         jdbi.withExtension(AccountRepository.class,
                dao -> dao.save(account));
    }
    
	public void update(Long id, BigDecimal balance) {
		 jdbi.withExtension(AccountRepository.class, 
				dao -> dao.update(id, balance));
	}
    
    public List<Account> findAll() {
    	return jdbi.withExtension(AccountRepository.class,
    			dao -> dao.findAll());
    }
    
    public Account findById(Long id) {
    	return jdbi.withExtension(AccountRepository.class,
    			dao -> dao.findById(id));
    }
}
