package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.mapper.PromotionMapper;
import eu.senla.naumovich.repositories.PromotionRepository;
import eu.senla.naumovich.services.impl.PromotionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PromotionServiceTest {
    @Mock
    private PromotionMapper mapper;
    @Mock
    private PromotionRepository repository;
    @InjectMocks
    private PromotionServiceImpl service;

    @Test
    void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Promotion promotion = new Promotion();
        Page<Promotion> promotionPage = new PageImpl<>(Collections.singletonList(promotion));
        when(repository.findAll(pageable)).thenReturn(promotionPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(PromotionShortDto.builder().build()));
        List<PromotionShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    void getByIdTest() {
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
    void updateTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(mapper.toEntity(promotionDto)).thenReturn(promotion);
        when(repository.save(promotion)).thenReturn(promotion);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.update(promotionDto);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).save(promotion);
        verify(mapper).toEntity(promotionDto);
        verify(mapper).toDto(promotion);
    }

    @Test
    void createTest() {
        Promotion promotion = Generator.createPromotion();
        PromotionDto promotionDto = Generator.createPromotionDto();
        when(mapper.toEntity(promotionDto)).thenReturn(promotion);
        when(repository.save(promotion)).thenReturn(promotion);
        when(mapper.toDto(promotion)).thenReturn(promotionDto);
        PromotionDto result = service.create(promotionDto);
        Assertions.assertEquals(promotionDto, result);
        verify(repository).save(promotion);
        verify(mapper).toEntity(promotionDto);
        verify(mapper).toDto(promotion);
    }

    @Test
    void deleteTest() {
        Promotion promotion = Generator.createPromotion();
        doNothing().when(repository).deleteById(promotion.getId());
        service.delete(promotion.getId());
        verify(repository).deleteById(promotion.getId());
    }
}
