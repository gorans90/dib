package com.dib.demo;

import com.dib.demo.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DibApplication implements ApplicationRunner {

	@Autowired
	private BeerService beerService;

	public static void main(String[] args) {
		SpringApplication.run(DibApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		beerService.initialize();
	}
}
