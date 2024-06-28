package eu.senla.naumovich.dto.address;

import eu.senla.naumovich.dto.publisher.PublisherDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AddressDto{
    private Long id;
    @NotBlank(message = "Field must be filled in")
    private String city;
    @NotBlank(message = "Field must be filled in")
    private String street;
    private int index;
    @NotNull(message = "Publisher information must be provided")
    private PublisherDto publisher;
}
