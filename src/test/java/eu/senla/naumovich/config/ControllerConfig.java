package eu.senla.naumovich.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "eu.senla.naumovich")
@Configuration
public class ControllerConfig {
}
