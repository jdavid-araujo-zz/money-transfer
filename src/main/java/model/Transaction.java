package model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

	private Long id;
	private Long fromAccount;
	private Long toAccount;
	private BigDecimal amount;
	private Date createDate;

	public Transaction() {}
	
	public Transaction(Long id, Long fromAccount, Long toAccount, BigDecimal amount) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
	
	
	public Transaction(Long fromAccount, Long toAccount, BigDecimal amount) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
	
	public Transaction(Long fromAccount, Long toAccount, BigDecimal amount, Date createDate) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.createDate = createDate;
	}

	public Transaction(Long id, Long fromAccount, Long toAccount, BigDecimal amount, Date createDate) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
