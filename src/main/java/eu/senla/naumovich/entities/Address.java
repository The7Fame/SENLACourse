package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    Long id;
    String street;
    Long index;
    Publisher publisher;
}
