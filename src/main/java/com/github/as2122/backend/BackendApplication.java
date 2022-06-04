package com.github.as2122.backend;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.accounts.StaticAccountManager;
import com.github.as2122.backend.api.RequestDeserializer;
import com.github.as2122.backend.api.requests.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class BackendApplication {

	@Bean
	public Gson jsonParser() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Request.class, new RequestDeserializer());

		return builder.create();
	}

	@Bean
	public AccountManagerInterface accountManager(){
		return new StaticAccountManager();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
