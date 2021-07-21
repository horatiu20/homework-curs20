package ro.fasttrackit.curs20.homework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.repository.TransactionRepository;

import java.util.List;

/*Create a webapp backend that manages the transactions in a budget:
	Transaction
		- id
		- product
		- type: SELL/BUY
		- amount: double

GET /transactions - get all transactions. Make it filterable by type, minAmount, maxAmount (you will have 6 filtering methods in repository: byType, byMinAmoun, byMaxAmout, byTypeAndMin, byTypeAndMax, byMinAndMax, byTypeAndMinAndMax)
GET /transactions/{id} - get transaction with id
POST /transactions - adds a new transaction
PUT  /transactions/{id} - replaces the transaction with id
PATCH /transactions/{id} - supports changing the product and the amount
DELETE /transactions/{id} - deletes the transaction with id
GET /transactions/reports/type -> returns a map from type to sum of amount - the processing is done in memory, not in the database. you can try making another implementation with calculations in db
GET /transactions/reports/product -> returns a map from product to sum of amount

		example:
		If you have transactions
		[
		{type:SELL, amount:3.2},
		{type:BUY, amount:4.0},
		{type:SELL, amount:1.3}
		]

		the type report will be
		{
		SELL: [
		{type:SELL, amount:3.2},
		{type:SELL, amount:1.3}
		],
		BUY: [{type:BUY, amount:4.0}]
		}*/

@SpringBootApplication
public class BudgetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetAppApplication.class, args);
	}

	@Bean
	CommandLineRunner atStartup(TransactionRepository repository) {
		return args -> {
			repository.saveAll(List.of(
					new Transaction("laptop", Type.BUY, 1000),
					new Transaction("headphones", Type.BUY, 100),
					new Transaction("player", Type.BUY, 250),
					new Transaction("tv", Type.SELL, 750),
					new Transaction("car", Type.SELL, 10000),
					new Transaction("fridge", Type.SELL, 500)
			));
		};
	}
}
