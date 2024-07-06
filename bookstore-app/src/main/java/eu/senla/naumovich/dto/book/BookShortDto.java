package eu.senla.naumovich.dto.book;

import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class BookShortDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String isbn;
    private List<PromotionShortDto> promotions;
}
