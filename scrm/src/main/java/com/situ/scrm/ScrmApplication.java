package com.situ.scrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.situ.scrm",annotationClass = Repository.class)
public class ScrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrmApplication.class, args);
	}

}
