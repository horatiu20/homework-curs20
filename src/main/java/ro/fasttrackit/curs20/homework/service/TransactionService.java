package ro.fasttrackit.curs20.homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public Optional<Transaction> deleteTransaction(int transactionId) {
		Optional<Transaction> transactionOptional = getById(transactionId);
		transactionOptional
				.ifPresent(transactionRepository::delete);
		return transactionOptional;
	}
}


