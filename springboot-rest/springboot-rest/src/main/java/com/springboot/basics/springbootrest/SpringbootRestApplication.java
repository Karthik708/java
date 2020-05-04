package com.springboot.basics.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootRestApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringbootRestApplication.class, args);
		
		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println("name -- "+name);
		}
	}

}
