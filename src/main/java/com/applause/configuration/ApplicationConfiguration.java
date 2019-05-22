package com.applause.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
@ComponentScan(value = {"com.applause.service", "com.applause.mapper", "com.applause.web", "com.applause.configuration.**"})
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@Configuration
public class ApplicationConfiguration {

    @EntityScan(basePackages = {"com.applause.model",})
    @EnableJpaRepositories(basePackages = {"com.applause.repository"})
    @Configuration
    public static class ApplicationRepositoryConfiguration {
    }

}
