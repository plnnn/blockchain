package lt.viko.eif.nychyporuk.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application for the blockchain service.
 * <p>
 * This class is responsible for bootstrapping and launching a Spring application.
 * It triggers the auto-configuration, component scanning, and other aspects of the
 * Spring framework to wire up the context needed to run the blockchain service.
 * </p>
 */
@SpringBootApplication
public class BlockchainApplication {

	/**
	 * The main method that serves as the entry point for the Spring Boot application.
	 * It delegates to the {@link SpringApplication} class to bootstrap the application,
	 * starting the autoconfigured embedded web server and initializing the Spring
	 * application context.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BlockchainApplication.class, args);
	}
}

