package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.services.mapper.PromotionMapper;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public List<PromotionDto> getAll() {
        List<Promotion> promotions = promotionRepository.getAll();
        List<PromotionDto> promotionsDto = promotions.stream()
                .map(promotionMapper::toDto)
                .collect(Collectors.toList());
        return promotionsDto;
    }

    @Override
    public PromotionDto getById(PromotionDto promotion) {
        return promotionMapper.toDto(promotionRepository.getById(promotion.getId()));
    }

    @Override
    public PromotionDto update(PromotionDto promotion) {
        return promotionMapper.toDto(promotionRepository.update(promotionMapper.toEntity(promotion)));
    }

    @Override
    public void create(PromotionDto promotion) {
        promotionRepository.create(promotionMapper.toEntity(promotion));
    }

    @Override
    public void delete(PromotionDto promotion) {
        promotionRepository.delete(promotionMapper.toEntity(promotion));
    }
}
