package ro.fasttrackit.curs20.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByType(Type transactionType);

	List<Transaction> findByMinAmount(double minAmount);

	List<Transaction> findByMaxAmount(double maxAmount);

	List<Transaction> findByTypeAndMinAmount(Type transactionType, double minAmount);

	List<Transaction> findByTypeAndMaxAmount(Type transactionType, double maxAmount);

	List<Transaction> findByMinAndMax(double minAmount, double maxAmount);

	List<Transaction> findByTypeAndMinAndMax(Type transactionType, double minAmount, double maxAmount);


}
