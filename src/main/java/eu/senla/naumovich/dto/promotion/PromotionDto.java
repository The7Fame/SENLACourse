package eu.senla.naumovich.dto.promotion;

import java.math.BigDecimal;
import java.util.List;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Field must be filled in")
    private String promotionName;
    @DecimalMin(value = "1.00", message = "percent of promotion can not be less than 1.00%")
    @DecimalMax(value = "99.00", message = "percent of promotion can not be greater than 99.00%")
    @NotNull(message = "Field must be filled in")
    private BigDecimal percent;
    private List<BookShortDto> books;
}
