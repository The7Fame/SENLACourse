package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    PromotionDto toDto(Promotion promotion);

    Promotion toEntity(PromotionDto promotionDto);
}
