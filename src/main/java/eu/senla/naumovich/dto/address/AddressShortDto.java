package eu.senla.naumovich.dto.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AddressShortDto {
    private Long id;
    private String city;
    private String street;
    private int index;
}