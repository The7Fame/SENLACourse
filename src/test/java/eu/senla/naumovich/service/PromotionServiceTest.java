package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.mapper.PromotionMapper;
import eu.senla.naumovich.services.impl.PromotionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        List<PromotionDto> promotionDtos = new ArrayList<>();
        List<Promotion> promotions = new ArrayList<>();
        promotionDtos.add(promotionDto);
        promotions.add(promotion);
        when(repository.getAll()).thenReturn(promotions);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        List<PromotionDto> result = service.getAll();
        Assertions.assertEquals(promotionDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(repository.getById(1L)).thenReturn(promotion);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.getById(1L);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).getById(1L);
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
        doNothing().when(repository).create(promotion);
        service.create(promotionDto);
        verify(repository).create(promotion);
        verify(mapper).toEntity(promotionDto);
    }

    @Test
    public void deleteTest() {
        Promotion promotion = Generator.createPromotion();
        when(repository.getById(promotion.getId())).thenReturn(promotion);
        doNothing().when(repository).delete(promotion);
        service.delete(promotion.getId());
        verify(repository).getById(promotion.getId());
        verify(repository).delete(promotion);
    }
}
