package ro.fasttrackit.curs20.homework.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	@GetMapping("byType")
	List<Transaction> findByType(@RequestParam(required = false) Type transactionType) {
		return transactionService.findByType(transactionType);
	}

	@PostMapping
	Transaction postTransaction(@RequestBody Transaction transaction) {
		return transactionService.postTransaction(transaction);
	}

	@DeleteMapping("{transactionId}")
	Transaction deleteTransaction(@PathVariable int transactionId){
		return transactionService.deleteTransaction(transactionId)
				.orElse(null);
	}
}
