package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends InterfaceMapper<Promotion, PromotionDto> {
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);
}
