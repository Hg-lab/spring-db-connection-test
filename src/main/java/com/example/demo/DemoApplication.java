package com.example.demo;

import com.example.demo.connection.DBConnectionUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Connection firstConnection = DBConnectionUtils.getFirstConnection();
		System.out.println("firstConnection = " + firstConnection);



		Connection secondConnection = DBConnectionUtils.getSecondConnection();
		System.out.println("secondConnection = " + secondConnection);

	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource-first")
	public HikariDataSource firstDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource-second")
	public HikariDataSource secondDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}
