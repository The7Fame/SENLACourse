package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.services.mapper.AddressMapper;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressDto> getAll() {
        List<Address> addresses = addressRepository.getAll();
        List<AddressDto> addressesDto = addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
        return addressesDto;
    }

    @Override
    public AddressDto getById(AddressDto address) {
        return addressMapper.toDto(addressRepository.getById(address.getId()));
    }

    @Override
    public AddressDto update(AddressDto address) {
        return addressMapper.toDto(addressRepository.update(addressMapper.toEntity(address)));
    }

    @Override
    public void create(AddressDto address) {
        addressRepository.create(addressMapper.toEntity(address));
    }

    @Override
    public void delete(AddressDto address) {
        addressRepository.delete(addressMapper.toEntity(address));
    }
}
