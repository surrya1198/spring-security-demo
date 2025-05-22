package com.example.security.Springsecuritydemo;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "transactionManagerDemo", entityManagerFactoryRef = "entityManagerFactoryDemo")
public class DataBaseConfig {

	@Bean(name = "datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName("org.h2.Driver").url("jdbc:h2:mem:secu;INIT=CREATE SCHEMA IF NOT EXISTS SECUREDB").username("test")
				.password("test").build();

	}

	@Bean(name = "entityManagerFactoryDemo")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource dataSource,
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).packages("com.example.security.Springsecuritydemo.entity")
				.persistenceUnit("primary").build();

	}

	@Bean(name = "transactionManagerDemo")
	public PlatformTransactionManager transactionManagerDemo(@Qualifier("entityManagerFactoryDemo") EntityManagerFactory entity) {

		return new JpaTransactionManager(entity);
	}

}
