package com.gtfs.tripplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "com.gtfs.tripplanning.repository")
public class TripplanningApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripplanningApplication.class, args);
	}

}
