package com.aihua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aihua.dao")
public class GeneratorStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorStartApplication.class, args);
	}
}
