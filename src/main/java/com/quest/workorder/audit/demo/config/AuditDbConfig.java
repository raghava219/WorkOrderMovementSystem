package com.quest.workorder.audit.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "auditEntityManagerFactory",
    transactionManagerRef = "auditTransactionManager", 
    basePackages = {"com.quest.workorder.audit.repository"})
@EnableJpaAuditing

public class AuditDbConfig {

  @Bean(name = "auditDataSource")
  @ConfigurationProperties(prefix = "audit.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "auditEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean auditEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("auditDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.quest.workorder.audit.domain").persistenceUnit("Audit_Work_Order").build();
  }

  @Bean(name = "auditTransactionManager")
  public PlatformTransactionManager auditTransactionManager(@Qualifier("auditEntityManagerFactory") EntityManagerFactory auditEntityManagerFactory) {
    return new JpaTransactionManager(auditEntityManagerFactory);
  }
  
}