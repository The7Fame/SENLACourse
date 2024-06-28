package eu.senla.naumovich.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "eu.senla.naumovich")
public class ControllerConfig {
}
