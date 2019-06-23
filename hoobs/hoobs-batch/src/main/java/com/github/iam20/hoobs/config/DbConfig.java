package com.github.iam20.hoobs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DbConfig {
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("hoobsDataSource")
	@ConfigurationProperties(prefix = "hoobs.datasource")
	public DataSource hoobsDataSource() {
		return DataSourceBuilder.create().build();
	}
}
