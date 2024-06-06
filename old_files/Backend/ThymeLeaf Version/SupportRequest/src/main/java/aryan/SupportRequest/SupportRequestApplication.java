package aryan.SupportRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SupportRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportRequestApplication.class, args);
		
		
	}

}
