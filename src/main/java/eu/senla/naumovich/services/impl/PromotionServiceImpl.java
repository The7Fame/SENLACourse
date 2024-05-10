package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PromotionMapper;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public List<PromotionDto> getAll(int size, int page) {
        List<Promotion> promotions = promotionRepository.getAll(size, page);
        return promotionMapper.toDtoList(promotions);
    }

    @Override
    public PromotionDto getById(Long id) {

        return promotionMapper.toDto(
                promotionRepository.findById(id).orElseThrow(() -> new NoRecords("No record with such ID " + id)));

    }

    @Override
    public PromotionDto update(PromotionDto promotion) {

        return promotionMapper.toDto(promotionRepository.update(promotionMapper.toEntity(promotion)));

    }

    @Override
    public PromotionDto create(PromotionDto promotion) {
        return promotionMapper.toDto(promotionRepository.create(promotionMapper.toEntity(promotion)));

    }

    @Override
    public void delete(Long id) {

        promotionRepository.deleteById(id);

    }
}
