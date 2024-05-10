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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void getAllTest() {
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<AddressDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(address));
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.getById(1L);
        Assertions.assertEquals(addressDto, result);
        verify(repository).findById(1L);
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
        when(repository.create(address)).thenReturn(address);
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.create(addressDto);
        Assertions.assertEquals(addressDto, result);
        verify(repository).create(address);
        verify(mapper).toEntity(addressDto);
        verify(mapper).toDto(address);
    }

    @Test
    public void deleteTest() {
        Address address = Generator.createAddress();
        doNothing().when(repository).deleteById(address.getId());
        service.delete(address.getId());
        verify(repository).deleteById(address.getId());
    }
}
