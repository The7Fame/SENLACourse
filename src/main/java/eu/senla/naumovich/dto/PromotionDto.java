package eu.senla.naumovich.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    Long id;
    String promotionName;
    BigDecimal percent;
}
