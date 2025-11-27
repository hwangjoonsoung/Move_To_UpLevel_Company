package me.mtuc.conference.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "me.mtuc.conference.institute",
        entityManagerFactoryRef = "instituteEntityManagerFactory",
        transactionManagerRef = "instituteTransactionManager"
)
public class InstituteDataBaseConfig {

    @Bean(name = "instituteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db-institute")
    public DataSource instituteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "instituteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("instituteDataSource") DataSource DataSourceBuilder
    ) {
        return entityManagerFactoryBuilder.dataSource(DataSourceBuilder)
                .packages("me.mtuc.conference.institute")
                .persistenceUnit("db-institute").build();
    }

    @Bean(name = "instituteTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("instituteEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
