package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.services.impl.AddressServiceImpl;
import eu.senla.naumovich.mapper.AddressMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressMapper mapper;
    @Mock
    AddressRepository repository;
    @InjectMocks
    private AddressServiceImpl service;

    @Test
    public void getAllTest(){
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        List<AddressDto> addressDtos = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        addressDtos.add(addressDto);
        addresses.add(address);
        when(repository.getAll()).thenReturn(addresses);
        when(mapper.toDto(address)).thenReturn(addressDto);
        List<AddressDto> result = service.getAll();
        Assertions.assertEquals(addressDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest(){
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(repository.getById(1L)).thenReturn(address);
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.getById(1L);
        Assertions.assertEquals(addressDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(address);
    }

    @Test
    public void updateTest() {
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(mapper.toEntity(addressDto)).thenReturn(address);
        when(repository.update(address)).thenReturn(address);
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.update(addressDto);
        Assertions.assertEquals(addressDto, result);
        verify(repository).update(address);
        verify(mapper).toEntity(addressDto);
        verify(mapper).toDto(address);
    }

    @Test
    public void createTest() {
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(mapper.toEntity(addressDto)).thenReturn(address);
        doNothing().when(repository).create(address);
        service.create(addressDto);
        verify(repository).create(address);
        verify(mapper).toEntity(addressDto);
    }
    @Test
    public void deleteTest() {
        Address address = Generator.createAddress();
        when(repository.getById(address.getId())).thenReturn(address);
        doNothing().when(repository).delete(address);
        service.delete(address.getId());
        verify(repository).getById(address.getId());
        verify(repository).delete(address);
    }
}
