package com.github.as2122.backend;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.accounts.StaticAccountManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	private final AccountManagerInterface accountManager = new StaticAccountManager();

	@Bean
	public AccountManagerInterface accountManager(){
		return accountManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
