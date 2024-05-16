package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.services.service.common.AbstractService;
import org.springframework.transaction.annotation.Transactional;

public interface PromotionService extends AbstractService<PromotionDto> {
    @Transactional
    PromotionDto createPromotionByGenre(CreatePromotionGenreDto promotionDto);

    @Transactional
    PromotionDto createPromotionByAuthor(CreatePromotionAuthorDto promotionDto);
}
