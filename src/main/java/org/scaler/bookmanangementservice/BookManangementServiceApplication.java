package org.scaler.bookmanangementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookManangementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManangementServiceApplication.class, args);
	}

}
