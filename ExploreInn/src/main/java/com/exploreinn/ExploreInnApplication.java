package com.exploreinn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"messages.properties"})
public class ExploreInnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExploreInnApplication.class, args);
	}

}
