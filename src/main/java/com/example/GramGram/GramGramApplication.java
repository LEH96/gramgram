package com.example.GramGram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EntityListeners 가 작동하게 허용
public class GramGramApplication {

	public static void main(String[] args) {
		SpringApplication.run(GramGramApplication.class, args);
	}

}
