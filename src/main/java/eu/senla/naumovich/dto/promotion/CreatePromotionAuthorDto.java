package eu.senla.naumovich.dto.promotion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
public class CreatePromotionAuthorDto {
    String authorName;
    BigDecimal percent;
    String promotionName;
}
