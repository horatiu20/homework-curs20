package ro.fasttrackit.curs20.homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.repository.TransactionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public List<Transaction> getAllTransactions(String product, Type transactionType, Double minAmount, Double maxAmount) {
		return this.transactionRepository.findAll().stream()
				.filter(transaction -> product == null || transaction.getProduct().equalsIgnoreCase(product))
				.filter(transaction -> transactionType == null || transaction.getTransactionType() == transactionType)
				.filter(transaction -> minAmount == null || transaction.getAmount() >= minAmount)
				.filter(transaction -> maxAmount == null || transaction.getAmount() <= maxAmount)
				.collect(Collectors.toList());
	}

	public Optional<Transaction> getById(int transactionId) {
		return this.transactionRepository.findAll().stream()
				.filter(transaction -> transaction.getId() == transactionId)
				.findFirst();
	}

	public Transaction postTransaction(Transaction transaction) {
		transaction.setId(null);
		return transactionRepository.save(transaction);
	}

	public Optional<Transaction> putTransaction(int transactionId, Transaction newTransaction) {
		Optional<Transaction> replacedTransaction = deleteTransaction(transactionId);
		replacedTransaction
				.ifPresent(deleteTransaction -> postTransaction(newTransaction));
		return replacedTransaction;
	}

	public Optional<Transaction> deleteTransaction(int transactionId) {
		Optional<Transaction> transactionOptional = getById(transactionId);
		transactionOptional
				.ifPresent(transactionRepository::delete);
		return transactionOptional;
	}

	public Optional<Transaction> patchTransaction(int transactionId, Transaction transaction) {
		Optional<Transaction> transactionById = getById(transactionId);
		Optional<Transaction> patchedTransaction = transactionById
				.map(oldTransaction -> new Transaction(
						transaction.getProduct() != null ? transaction.getProduct() : oldTransaction.getProduct(),
						transaction.getTransactionType() != null ? transaction.getTransactionType() : oldTransaction.getTransactionType(),
						transaction.getAmount() != 0 ? transaction.getAmount() : oldTransaction.getAmount()));
		patchedTransaction.ifPresent(newTransaction -> putTransaction(transactionId, newTransaction));
		return patchedTransaction;
	}

	public Map<Type, List<Double>> mapTypeToAmount() {
		return transactionRepository.findAll().stream()
				.collect(groupingBy(Transaction::getTransactionType,
						mapping(Transaction::getAmount, toList())));
	}

	public Map<String, List<Double>> mapProductToAmount() {
		return transactionRepository.findAll().stream()
				.collect(groupingBy(Transaction::getProduct,
						mapping(Transaction::getAmount, toList())));
	}
}


