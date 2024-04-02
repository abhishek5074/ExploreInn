package com.exploreinn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ExploreInnConfig {

	@Bean
	public RestClient getRestClient() {
		return RestClient.create();
	}
}
