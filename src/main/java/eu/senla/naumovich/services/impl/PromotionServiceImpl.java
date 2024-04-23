package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.exceptions.NoRecords;
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
        try {
            List<Promotion> promotions = promotionRepository.getAll();
            List<PromotionDto> promotionsDto = promotions.stream()
                    .map(promotionMapper::toDto)
                    .collect(Collectors.toList());
            return promotionsDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public PromotionDto getById(Long id) {
        try {
            return promotionMapper.toDto(promotionRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public PromotionDto update(PromotionDto promotion) {
        try {
            return promotionMapper.toDto(promotionRepository.update(promotionMapper.toEntity(promotion)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + promotion.getId());
        }
    }

    @Override
    public void create(PromotionDto promotion) {
        try {
            promotionRepository.create(promotionMapper.toEntity(promotion));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Promotion promotion = promotionRepository.getById(id);
            promotionRepository.delete(promotion);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
