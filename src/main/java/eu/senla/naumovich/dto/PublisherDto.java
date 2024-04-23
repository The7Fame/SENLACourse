package eu.senla.naumovich.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {
    Long id;
    String publisherName;
    AddressDto address;
}
