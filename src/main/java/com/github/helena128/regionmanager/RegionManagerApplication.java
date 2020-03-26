package com.github.helena128.regionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RegionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegionManagerApplication.class, args);
	}
}
