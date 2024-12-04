package com.intranet.sample.configuration.datasource

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.intranet.sample.tobe"],
    entityManagerFactoryRef = "tobeEntityManagerFactory",
    transactionManagerRef = "tobeTransactionManager"
)
class TobeDataSource {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.tobe")
    fun dataSourceTobe(): DataSource = DataSourceBuilder.create().build()

    @Primary
    @Bean
    fun tobeEntityManagerFactory(
        @Qualifier("dataSourceTobe") dataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean = builder
        .dataSource(dataSource)
        .properties(mapOf("hibernate.hbm2ddl.auto" to "update"))
        .packages("com.intranet.sample.tobe")
        .persistenceUnit("tobe")
        .build()

    @Primary
    @Bean
    fun tobeTransactionManager(
        @Qualifier("tobeEntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(entityManagerFactory)

}