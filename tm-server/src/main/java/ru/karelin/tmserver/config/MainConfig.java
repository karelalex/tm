package ru.karelin.tmserver.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "ru.karelin.tmserver")
@PropertySource("classpath:app.properties")
public class MainConfig {
}
