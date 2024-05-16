package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.entities.enums.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionRepository extends AbstractRepository<Promotion, Long> {
    Promotion createPromotionByGenre(Integer genreId, BigDecimal percent, String promotionName);
    Promotion createPromotionByAuthor(String authorName, BigDecimal percent, String promotionName);
}
