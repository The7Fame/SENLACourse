package eu.senla.naumovich.dao.common;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
public class BaseRepositoryTest {
    public Pageable applyPage(){
        return PageRequest.of(1, 5);
    }
}
