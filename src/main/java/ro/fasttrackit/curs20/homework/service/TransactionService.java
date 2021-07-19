package ro.fasttrackit.curs20.homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public List<Transaction> findByType() {
		return transactionRepository.findByType(Type.BUY);
	}

	public Transaction postTransaction(Transaction transaction) {
		transaction.setId(null);
		return transactionRepository.save(transaction);
	}

	public Optional<Transaction> deleteTransaction(int transactionId){
		return transactionRepository.delete();
	}


}



