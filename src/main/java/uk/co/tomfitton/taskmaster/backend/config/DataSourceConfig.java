package uk.co.tomfitton.taskmaster.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { 
	"classpath:application.properties"
})
@EnableJpaRepositories(basePackages = {
	"uk.co.tomfitton.taskmaster.backend.domain"
})
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Value(value="${database.driver}")
	private String DATABASE_DRIVER;
	
	@Value(value="${database.jndiPrefix}")
	private String DATABASE_JNDI_PREFIX;
	
	@Value(value="${database.hostname}")
	private String DATABASE_HOSTNAME;
	
	@Value(value="${database.port}")
	private String DATABASE_PORT;
	
	@Value(value="${database.name}")
	private String DATABASE_NAME;
	
	@Value(value="${database.username}")
	private String DATABASE_USERNAME;
	
	@Value(value="${database.password}")
	private String DATABASE_PASSWORD;
	
	@Value(value="${database.domain.package}")
	private String DATABASE_DOMAIN_PACKAGE;

	@Value(value="${database.hibernate.dialect}")
	private String HIBERNATE_DIALECT;
	
	@Value(value="${database.hibernate.sql.show}")
	private String HIBERNATE_SQL_SHOW;
	
	@Value(value="${database.hibernate.sql.format}")
	private String HIBERNATE_SQL_FORMAT;
	
	@Value(value="${database.hibernate.ejb.namingStrategy}")
	private String HIBERNATE_EJB_NAMING_STRATEGY;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(getDatabaseUrl());
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
			= new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(DATABASE_DOMAIN_PACKAGE);
		
		Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        jpaProperties.put("hibernate.ejb.naming_strategy", HIBERNATE_EJB_NAMING_STRATEGY);
        jpaProperties.put("hibernate.show_sql", HIBERNATE_SQL_SHOW);
        jpaProperties.put("hibernate.format_sql", HIBERNATE_SQL_FORMAT);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
        
		return entityManagerFactoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(DataSource dataSource) {
		return new JpaTransactionManager();
	}
	
	private String getDatabaseUrl() {
		StringBuilder dataSourceUrlBuilder = new StringBuilder();
		dataSourceUrlBuilder.append(DATABASE_JNDI_PREFIX);
		dataSourceUrlBuilder.append("://");
		dataSourceUrlBuilder.append(DATABASE_HOSTNAME);
		dataSourceUrlBuilder.append(":");
		dataSourceUrlBuilder.append(DATABASE_PORT);
		dataSourceUrlBuilder.append("/");
		dataSourceUrlBuilder.append(DATABASE_NAME);
		return dataSourceUrlBuilder.toString();
	}
	
}
