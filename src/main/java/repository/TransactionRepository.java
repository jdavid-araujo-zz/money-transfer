package repository;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import model.Transaction;

public interface TransactionRepository {

	@SqlUpdate("insert into transaction (from_account, to_account, amount, create_date) VALUES (:fromAccount, :toAccount, :amount, :now)")
	@GetGeneratedKeys("id")
	@Timestamped
	Long save(@BindBean Transaction transaction);

	@SqlQuery("SELECT * FROM transaction where from_account=:fromAccountId")
	@RegisterBeanMapper(Transaction.class)
	List<Transaction> findAllTransactionByFromAccount(@Bind("fromAccountId") Long fromAccountId);

	@SqlQuery("SELECT * FROM transaction where to_account=:toAccountId")
	@RegisterBeanMapper(Transaction.class)
	List<Transaction> findAllTransactionByToAccount(@Bind("toAccountId") Long toAccountId);

	@SqlQuery("SELECT * FROM transaction where from_account=:fromAccountId and to_account=:toAccountId")
	@RegisterBeanMapper(Transaction.class)
	List<Transaction> findAllTransactionByFromAccountAndToAccount(@Bind("id") Long fromAccountId,
			@Bind("id") Long toAccountId);

	@SqlQuery("SELECT * FROM transaction order by id")
	@RegisterBeanMapper(Transaction.class)
	List<Transaction> findAll();
}
