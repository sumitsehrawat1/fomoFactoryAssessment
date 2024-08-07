package com.assessment.coin_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoinTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinTrackerApplication.class, args);
	}

}
