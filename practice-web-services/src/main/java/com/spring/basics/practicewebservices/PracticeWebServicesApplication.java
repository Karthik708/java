package com.spring.basics.practicewebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PracticeWebServicesApplication {

	public static void main(String[] args) {
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSortAlgorithm());
		
		ConfigurableApplicationContext applicationContext = 
				SpringApplication.run(PracticeWebServicesApplication.class, args);
		
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		
		int result = binarySearch.binarySearch(new int[] {1,2,0}, 0); 
		System.out.println("result :: " + result);
		
	}

}
