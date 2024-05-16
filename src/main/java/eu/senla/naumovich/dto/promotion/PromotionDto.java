package eu.senla.naumovich.dto.promotion;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PromotionDto {
    private Long id;
    private String promotionName;
    private BigDecimal percent;
}
