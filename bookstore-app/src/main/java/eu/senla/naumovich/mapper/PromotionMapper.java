package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper extends InterfaceMapper<Promotion, PromotionDto, PromotionShortDto> {
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);
}
