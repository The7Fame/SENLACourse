package eu.senla.naumovich;

import eu.senla.naumovich.configuration.ApplicationConfig;
import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.entities.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Role role = new Role();
        role.setRoleName("rolee");
        RoleRepository repository = context.getBean(RoleRepository.class);
        repository.create(role);
        System.out.println(repository.getAll());
    }
}
