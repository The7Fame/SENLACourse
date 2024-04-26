package eu.senla.naumovich.dto;

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
    private String publisherName;
    private AddressDto address;
}
