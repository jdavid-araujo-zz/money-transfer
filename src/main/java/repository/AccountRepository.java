package repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import model.Account;

public interface AccountRepository {

	@SqlUpdate("insert into account (owner, balance, create_date) VALUES (:owner, :balance, :now)")
	@GetGeneratedKeys("id")
	@Timestamped
	long save(@BindBean Account account);
	
	@SqlUpdate("update account set balance=:balance where id=:id")
	@RegisterBeanMapper(Account.class)
	Integer update(@Bind("id") Long id, @Bind("balance") BigDecimal balance);

	@SqlQuery("SELECT * FROM account ORDER BY id")
	@RegisterBeanMapper(Account.class)
	List<Account> findAll();
	
	@SqlQuery("SELECT * FROM account where id=:id")
	@RegisterBeanMapper(Account.class)
	Account findById(@Bind("id") Long id);
}
