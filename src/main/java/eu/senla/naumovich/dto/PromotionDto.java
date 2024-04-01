package eu.senla.naumovich.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PromotionDto {
    Long id;
    String promotionName;
    Double percent;
}
