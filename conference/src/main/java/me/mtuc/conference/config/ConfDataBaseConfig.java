package me.mtuc.conference.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "me.mtuc.conference.conf",
        entityManagerFactoryRef = "confEntityManagerFactory",
        transactionManagerRef = "confTransactionManager"
)
public class ConfDataBaseConfig {

    @Primary
    @Bean(name = "confDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db-conf")
    public DataSource confDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "confEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("confDataSource") DataSource datasource) {
        return entityManagerFactoryBuilder.dataSource(datasource).packages("me.mtuc.conference.conf")
                .persistenceUnit("db-conf").build();
    }

    @Primary
    @Bean(name = "confTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("confEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}

