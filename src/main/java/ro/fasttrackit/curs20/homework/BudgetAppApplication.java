package ro.fasttrackit.curs20.homework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs20.homework.entity.Transaction;
import ro.fasttrackit.curs20.homework.entity.Type;
import ro.fasttrackit.curs20.homework.repository.TransactionRepository;

import java.util.List;

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
