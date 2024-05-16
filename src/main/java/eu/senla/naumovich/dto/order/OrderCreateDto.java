package eu.senla.naumovich.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class OrderCreateDto {
    private int id;
}
