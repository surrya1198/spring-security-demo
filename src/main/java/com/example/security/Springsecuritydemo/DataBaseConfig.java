package com.example.security.Springsecuritydemo;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "transactionManagerDemo", entityManagerFactoryRef = "entityManagerFactoryDemo")
public class DataBaseConfig {

	@Bean(name = "datasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName("org.h2.Driver")
				.url("jdbc:h2:file:./secu;DB_CLOSE_DELAY=-1;INIT=CREATE SCHEMA IF NOT EXISTS SECUREDB").username("test")
				.password("test").build();

	}

	@Bean(name = "entityManagerFactoryDemo")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource dataSource,
			EntityManagerFactoryBuilder builder) {
		 Map<String, Object> jpaProperties = new HashMap<>();
		    jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // or update
		    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		    jpaProperties.put("hibernate.show_sql", "true");
		return builder.dataSource(dataSource).packages("com.example.security.Springsecuritydemo.entity")
				.persistenceUnit("primary").properties(jpaProperties).build();

	}

	@Bean(name = "transactionManagerDemo")
	@Primary
	public PlatformTransactionManager transactionManagerDemo(
			@Qualifier("entityManagerFactoryDemo") EntityManagerFactory entity) {

		return new JpaTransactionManager(entity);
	}

}
