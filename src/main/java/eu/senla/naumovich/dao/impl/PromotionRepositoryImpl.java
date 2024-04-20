package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Promotion;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionRepositoryImpl extends AbstractDao<Long, Promotion> implements PromotionRepository {
    @Override
    protected Class<Promotion> getEntityClass() {
        return Promotion.class;
    }
}
