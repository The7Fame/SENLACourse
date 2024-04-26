package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.mapper.PublisherMapper;
import eu.senla.naumovich.services.impl.PublisherServiceImpl;

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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {
    @Mock
    private PublisherMapper mapper;
    @Mock
    PublisherRepository repository;
    @InjectMocks
    private PublisherServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        List<PublisherDto> result = service.getAll();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Publisher publisher = Generator.createPublisher();
        PublisherDto publisherDto = Generator.createPublisherDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(publisher));
        when(mapper.toDto(publisher)).thenReturn(publisherDto);
        PublisherDto result = service.getById(1L);
        Assertions.assertEquals(publisherDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(publisher);
    }

    @Test
    public void updateTest() {
        Publisher publisher = Generator.createPublisher();
        PublisherDto publisherDto = Generator.createPublisherDto();
        when(mapper.toEntity(publisherDto)).thenReturn(publisher);
        when(repository.update(publisher)).thenReturn(publisher);
        when(mapper.toDto(publisher)).thenReturn(publisherDto);
        PublisherDto result = service.update(publisherDto);
        Assertions.assertEquals(publisherDto, result);
        verify(repository).update(publisher);
        verify(mapper).toEntity(publisherDto);
        verify(mapper).toDto(publisher);
    }

    @Test
    public void createTest() {
        Publisher publisher = Generator.createPublisher();
        PublisherDto publisherDto = Generator.createPublisherDto();
        when(mapper.toEntity(publisherDto)).thenReturn(publisher);
        when(repository.create(publisher)).thenReturn(publisher);
        when(mapper.toDto(publisher)).thenReturn(publisherDto);
        PublisherDto result = service.create(publisherDto);
        Assertions.assertEquals(publisherDto, result);
        verify(repository).create(publisher);
        verify(mapper).toEntity(publisherDto);
        verify(mapper).toDto(publisher);
    }

    @Test
    public void deleteTest() {
        Publisher publisher = Generator.createPublisher();
        doNothing().when(repository).deleteById(publisher.getId());
        service.delete(publisher.getId());
        verify(repository).deleteById(publisher.getId());
    }
}
