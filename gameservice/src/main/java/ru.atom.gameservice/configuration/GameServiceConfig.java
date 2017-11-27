package ru.atom.gameservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:gameservice.properties")
public class GameServiceConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
