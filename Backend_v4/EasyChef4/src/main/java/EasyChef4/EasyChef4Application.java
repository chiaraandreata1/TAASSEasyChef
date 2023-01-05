package EasyChef4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EasyChef4Application {

	public static void main(String[] args) {
		SpringApplication.run(EasyChef4Application.class, args);
	}

}
