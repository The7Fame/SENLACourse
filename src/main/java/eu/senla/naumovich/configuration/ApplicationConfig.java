package eu.senla.naumovich.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.integration.spring.SpringLiquibase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "eu.senla.naumovich")
@PropertySource("classpath:application.properties")
public class
ApplicationConfig {
    @Value("${spring.datasource.url}")
    private String databaseURI;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.liquibase.change-log}")
    private String changelog;
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(driverName);
        managerDataSource.setUrl(databaseURI);
        managerDataSource.setUsername(username);
        managerDataSource.setPassword(password);
        return managerDataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog(changelog);
        return liquibase;
    }
}
