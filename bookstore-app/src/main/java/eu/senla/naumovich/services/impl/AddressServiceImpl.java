package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.dto.address.AddressShortDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.mapper.AddressMapper;
import eu.senla.naumovich.repositories.AddressRepository;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressShortDto> getAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        Page<Address> addressPage = addressRepository.findAll(pageable);
        return addressMapper.toDtoList(addressPage.getContent());
    }

    @Override
    public AddressDto getById(Long id) {
        return addressMapper
                .toDto(addressRepository.findById(id).orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
    }

    @Override
    public AddressDto update(AddressDto address) {
        return addressMapper.toDto(addressRepository.save(addressMapper.toEntity(address)));
    }

    @Override
    public AddressDto create(AddressDto address) {
        return addressMapper.toDto(addressRepository.save(addressMapper.toEntity(address)));
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
