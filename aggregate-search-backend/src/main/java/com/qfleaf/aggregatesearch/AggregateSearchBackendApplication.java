package com.qfleaf.aggregatesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.qfleaf.aggregatesearch.repository")
@EnableElasticsearchRepositories(basePackages = "com.qfleaf.aggregatesearch.esdao")
@EntityScan(basePackages = "com.qfleaf.aggregatesearch.model.entity")
@EnableScheduling
public class AggregateSearchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregateSearchBackendApplication.class, args);
    }

}
