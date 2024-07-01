package eu.senla.naumovich.dao.common;

import eu.senla.naumovich.Application;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class BaseRepositoryTest {
    public Pageable applyPage(){
        return PageRequest.of(1, 5);
    }
}
