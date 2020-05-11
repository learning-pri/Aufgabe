package com.eko.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableJpaAuditing
@PropertySources({@PropertySource(value = "file:${external.config}", ignoreResourceNotFound = true) })
public class AufgabeApplication {
	public static void main(String[] args) {

		ApiContextInitializer.init();
		SpringApplication.run(AufgabeApplication.class, args);
	}
}