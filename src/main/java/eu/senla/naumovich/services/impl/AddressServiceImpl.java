package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.AddressMapper;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressDto> getAll() {
        List<Address> addresses = addressRepository.getAll();
        if (addresses.isEmpty()) {
            return Collections.emptyList();
        }
        return addressMapper.toDtoList(addresses);
    }

    @Override
    public AddressDto getById(Long id) {
        return addressMapper
                .toDto(addressRepository.findById(id).orElseThrow(() -> new NoRecords("No record with such ID " + id)));
    }

    @Override
    public AddressDto update(AddressDto address) {
        return addressMapper.toDto(addressRepository.update(addressMapper.toEntity(address)));
    }

    @Override
    public AddressDto create(AddressDto address) {
        return addressMapper.toDto(addressRepository.create(addressMapper.toEntity(address)));
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
