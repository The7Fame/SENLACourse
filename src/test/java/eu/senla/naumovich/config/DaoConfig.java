package eu.senla.naumovich.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "eu.senla.naumovich.dao")
public class DaoConfig {
}
