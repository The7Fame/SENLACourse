package eu.senla.naumovich.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PublisherDto {
    private Long id;
    @NotBlank(message = "Field must be filled in")
    private String publisherName;
}
