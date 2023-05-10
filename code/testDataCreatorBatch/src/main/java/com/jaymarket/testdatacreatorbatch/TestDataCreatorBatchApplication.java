package com.jaymarket.testdatacreatorbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class TestDataCreatorBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDataCreatorBatchApplication.class, args);
	}

}
