package home.work.grpc_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GrpcServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(GrpcServerApplication.class, args);
	}
}