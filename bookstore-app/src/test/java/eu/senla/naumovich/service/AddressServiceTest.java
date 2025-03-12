package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.dto.address.AddressShortDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.kafka.KafkaSenderService;
import eu.senla.naumovich.repositories.AddressRepository;
import eu.senla.naumovich.services.impl.AddressServiceImpl;
import eu.senla.naumovich.mapper.AddressMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressMapper mapper;
    @Mock
    private AddressRepository repository;
    @Mock
    private KafkaSenderService kafkaSenderService;
    @InjectMocks
    private AddressServiceImpl service;

    @Test
    void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Address address = new Address();
        Page<Address> addressPage = new PageImpl<>(Collections.singletonList(address));
        when(repository.findAll(pageable)).thenReturn(addressPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(AddressShortDto.builder().build()));
        List<AddressShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    void getByIdTest() {
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
    void updateTest() {
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(mapper.toEntity(addressDto)).thenReturn(address);
        when(repository.save(address)).thenReturn(address);
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.update(addressDto);
        Assertions.assertEquals(addressDto, result);
        verify(repository).save(address);
        verify(mapper).toEntity(addressDto);
        verify(mapper).toDto(address);
        verify(kafkaSenderService).sendAddress(eq(addressDto));

    }

    @Test
    void createTest() {
        Address address = Generator.createAddress();
        AddressDto addressDto = Generator.createAddressDto();
        when(mapper.toEntity(addressDto)).thenReturn(address);
        when(repository.save(address)).thenReturn(address);
        when(mapper.toDto(address)).thenReturn(addressDto);
        AddressDto result = service.create(addressDto);
        Assertions.assertEquals(addressDto, result);
        verify(repository).save(address);
        verify(mapper).toEntity(addressDto);
        verify(mapper).toDto(address);
    }

    @Test
    void deleteTest() {
        Address address = Generator.createAddress();
        doNothing().when(repository).deleteById(address.getId());
        service.delete(address.getId());
        verify(repository).deleteById(address.getId());
    }
}
