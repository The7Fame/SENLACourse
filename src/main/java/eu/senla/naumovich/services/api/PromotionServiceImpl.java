package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PromotionDto> getAll() {
        List<PromotionDto> promotionsDto = new ArrayList<>();
        List<Promotion> promotions = promotionRepository.getAll();
        for(Promotion promotion : promotions){
            promotionsDto.add(modelMapper.map(promotion, PromotionDto.class));
        }
        return promotionsDto;
    }

    @Override
    public PromotionDto getById(PromotionDto promotion) {
        return modelMapper.map(promotionRepository.getById(modelMapper.map(promotion, Promotion.class)), PromotionDto.class);
    }

    @Override
    public PromotionDto update(PromotionDto promotion) {
        return modelMapper.map(promotionRepository.update(modelMapper.map(promotion, Promotion.class)), PromotionDto.class);
    }

    @Override
    public PromotionDto create(PromotionDto promotion) {
        return modelMapper.map(promotionRepository.create(modelMapper.map(promotion, Promotion.class)), PromotionDto.class);
    }

    @Override
    public void delete(PromotionDto promotion) {
        promotionRepository.delete(modelMapper.map(promotion, Promotion.class));
    }
}
