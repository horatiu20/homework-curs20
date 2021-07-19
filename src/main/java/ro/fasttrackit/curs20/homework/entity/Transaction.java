package ro.fasttrackit.curs20.homework.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Transaction {
	@Id
	@GeneratedValue
	private Integer id;

	private String product;
	private Type transactionType;
	private double amount;

	public Transaction() {
	}

	public Transaction(String product, Type transactionType, double amount) {
		this.product = product;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Type getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Type transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id=" + id +
				", product='" + product + '\'' +
				", transactionType=" + transactionType +
				", amount=" + amount +
				'}';
	}
}
