package br.com.zup.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class GastoApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(GastoApiApplication.class, args);
	}
}
