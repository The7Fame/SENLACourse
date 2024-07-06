package eu.senla.naumovich.dto.promotion;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
public class CreatePromotionGenreDto {
    @NotNull(message = "Field must be filled in")
    Integer genreId;
    @DecimalMin(value = "1.00", message = "percent of promotion can not be less than 1.00%")
    @DecimalMax(value = "99.00", message = "percent of promotion can not be greater than 99.00%")
    @NotNull(message = "Field must be filled in")
    BigDecimal percent;
    @NotBlank(message = "Field must be filled in")
    String promotionName;
}
