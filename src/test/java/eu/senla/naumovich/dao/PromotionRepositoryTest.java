package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.data.Generator;

import eu.senla.naumovich.entities.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class PromotionRepositoryTest {
    @Autowired
    PromotionRepository repository;

    @Test
    public void createRecord() {
        Promotion promotion = Generator.createPromotion();
        repository.create(promotion);
        Assertions.assertEquals(promotion, repository.getById(promotion.getId()));
    }

    @Test
    public void updateRecord() {
        Promotion promotion = Generator.updatePromotion();
        repository.update(promotion);
        Assertions.assertEquals(promotion, repository.getById(promotion.getId()));
    }

    @Test
    public void deleteTest() {
        Promotion promotion = Generator.createPromotion();
        repository.delete(promotion);
        Assertions.assertNull(repository.getById(promotion.getId()));
    }
}
