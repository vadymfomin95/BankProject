package nure.ua.fomin.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		var springApplication = new SpringApplication(ConfigserverApplication.class);
		springApplication.addInitializers(new TruststoreInitializer());
		springApplication.run(args);
	}

}
