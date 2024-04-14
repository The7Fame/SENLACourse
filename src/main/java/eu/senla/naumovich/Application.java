package eu.senla.naumovich;

import eu.senla.naumovich.configuration.ApplicationConfig;
import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
