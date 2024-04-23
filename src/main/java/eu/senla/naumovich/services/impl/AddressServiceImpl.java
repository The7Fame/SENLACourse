package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.AddressMapper;
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
        try {
            List<Address> addresses = addressRepository.getAll();
            List<AddressDto> addressesDto = addresses.stream()
                    .map(addressMapper::toDto)
                    .collect(Collectors.toList());
            return addressesDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public AddressDto getById(Long id) {
        try {
            return addressMapper.toDto(addressRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public AddressDto update(AddressDto address) {
        try {
            return addressMapper.toDto(addressRepository.update(addressMapper.toEntity(address)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + address.getId());
        }
    }

    @Override
    public void create(AddressDto address) {
        try {
            addressRepository.create(addressMapper.toEntity(address));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Address address = addressRepository.getById(id);
            addressRepository.delete(address);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
