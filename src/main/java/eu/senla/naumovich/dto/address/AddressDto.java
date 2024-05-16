package eu.senla.naumovich.dto.address;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AddressDto {
    private Long id;
    private String city;
    private String street;
    private int index;
}
