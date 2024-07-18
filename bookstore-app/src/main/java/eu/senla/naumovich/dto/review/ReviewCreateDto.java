package eu.senla.naumovich.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ReviewCreateDto {
    @NotNull(message = "Rating cannot be null")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be no more than 5")
    private Long rating;
    @NotBlank(message = "Field must be filled in")
    private String text;
}
