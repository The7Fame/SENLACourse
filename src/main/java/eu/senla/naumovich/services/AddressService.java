package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAll();

    AddressDto getById(AddressDto address);

    AddressDto update(AddressDto address);

    AddressDto create(AddressDto address);

    void delete(AddressDto address);
}
