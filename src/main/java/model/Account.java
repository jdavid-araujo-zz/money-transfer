package model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
	
	private Long id;
	private String owner;
	private BigDecimal balance;
	private Date createDate;
	
	public Account() {}
	
	public Account(Long id, String owner, BigDecimal balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.balance = balance;
	}
	
	public Account(String owner, BigDecimal balance) {
		super();
		this.owner = owner;
		this.balance = balance;
	}
	
	public Account(String owner, BigDecimal balance, Date createDate) {
		super();
		this.owner = owner;
		this.balance = balance;
		this.createDate = createDate;
	}
	
	public Account(Long id, String owner, BigDecimal balance, Date createDate) {
		super();
		this.id = id;
		this.owner = owner;
		this.balance = balance;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
