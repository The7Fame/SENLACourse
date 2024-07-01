package eu.senla.naumovich.dao.common;

import eu.senla.naumovich.config.TestConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class BaseRepositoryTest {
    public Pageable applyPage(){
        return PageRequest.of(1, 5);
    }
}
