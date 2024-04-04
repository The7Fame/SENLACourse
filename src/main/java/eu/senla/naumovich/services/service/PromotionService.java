package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    List<PromotionDto> getAll();

    PromotionDto getById(PromotionDto promotion);

    PromotionDto update(PromotionDto promotion);

    PromotionDto create(PromotionDto promotion);

    void delete(PromotionDto promotion);
}
