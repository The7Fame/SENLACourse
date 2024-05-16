package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.mapper.PromotionMapper;
import eu.senla.naumovich.services.impl.PromotionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTest {
    @Mock
    private PromotionMapper mapper;
    @Mock
    PromotionRepository repository;
    @InjectMocks
    private PromotionServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<PromotionDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(promotion));
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.getById(1L);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(promotion);
    }

    @Test
    public void updateTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(mapper.toEntity(promotionDto)).thenReturn(promotion);
        when(repository.update(promotion)).thenReturn(promotion);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.update(promotionDto);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).update(promotion);
        verify(mapper).toEntity(promotionDto);
        verify(mapper).toDto(promotion);
    }

    @Test
    public void createTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(mapper.toEntity(promotionDto)).thenReturn(promotion);
        when(repository.create(promotion)).thenReturn(promotion);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.create(promotionDto);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).create(promotion);
        verify(mapper).toEntity(promotionDto);
        verify(mapper).toDto(promotion);
    }

    @Test
    public void deleteTest() {
        Promotion promotion = Generator.createPromotion();
        doNothing().when(repository).deleteById(promotion.getId());
        service.delete(promotion.getId());
        verify(repository).deleteById(promotion.getId());
    }
}
