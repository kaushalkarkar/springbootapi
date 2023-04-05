package com.mybank.test.model;

import jakarta.persistence.Id;

public class TransferRequest

{

	@Id
	public int fromAccountId;

	private int toAccountId;
	
	private String passwords;

	private Double amount;

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getPassword() {
		return passwords;
	}

	public void setPassword(String password) {
		this.passwords = password;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransferRequest(int fromAccountId, int toAccountId, String password, Double amount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.passwords = password;
		this.amount = amount;
	}

	public TransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TransferRequest [fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", password="
				+ passwords + ", amount=" + amount + "]";
	}

	
}
