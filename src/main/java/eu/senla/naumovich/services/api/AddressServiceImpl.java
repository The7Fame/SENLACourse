package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AddressDto> getAll() {
        List<AddressDto> addressesDto = new ArrayList<>();
        List<Address> addresses = addressRepository.getAll();
        for(Address address : addresses){
            addressesDto.add(modelMapper.map(address, AddressDto.class));
        }
        return addressesDto;
    }

    @Override
    public AddressDto getById(AddressDto address) {
        return modelMapper.map(addressRepository.getById(modelMapper.map(address, Address.class)), AddressDto.class);
    }

    @Override
    public AddressDto update(AddressDto address) {
        return modelMapper.map(addressRepository.update(modelMapper.map(address, Address.class)), AddressDto.class);
    }

    @Override
    public AddressDto create(AddressDto address) {
        return modelMapper.map(addressRepository.create(modelMapper.map(address, Address.class)), AddressDto.class);
    }

    @Override
    public void delete(AddressDto address) {
        addressRepository.delete(modelMapper.map(address, Address.class));
    }
}
