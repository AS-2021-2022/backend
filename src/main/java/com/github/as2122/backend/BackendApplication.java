package com.github.as2122.backend;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.accounts.StaticAccountManager;
import com.github.as2122.backend.api.requests.RequestDeserializer;
import com.github.as2122.backend.api.requests.Request;
import com.github.as2122.backend.chat.ChatManager;
import com.github.as2122.backend.tasks.TaskManager;
import com.github.as2122.backend.teams.TeamManager;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

	@Bean
	public ChatManager chatManager() {
		return new ChatManager(accountManager());
	}

	@Bean
	public WorkflowManager workflowManager() {
		return new WorkflowManager();
	}

	@Bean
	public TeamManager teamManager() {
		return new TeamManager(accountManager());
	}

	@Bean
	public TaskManager taskManager() {
		return new TaskManager();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
