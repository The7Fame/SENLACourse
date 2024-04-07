package eu.senla.naumovich.services.api;

import eu.senla.naumovich.annotation.Transaction;
import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publishers = publisherRepository.getAll();
        List<PublisherDto> publishersDto = publishers.stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .collect(Collectors.toList());
        return publishersDto;
    }

    @Override
    public PublisherDto getById(PublisherDto publisher) {
        return modelMapper.map(publisherRepository.getById(modelMapper.map(publisher, Publisher.class)), PublisherDto.class);
    }

    @Override
    public PublisherDto update(PublisherDto publisher) {
        return modelMapper.map(publisherRepository.update(modelMapper.map(publisher, Publisher.class)), PublisherDto.class);
    }

    @Override
    public PublisherDto create(PublisherDto publisher) {
        return modelMapper.map(publisherRepository.create(modelMapper.map(publisher, Publisher.class)), PublisherDto.class);
    }

    @Override
    public void delete(PublisherDto publisher) {
        publisherRepository.delete(modelMapper.map(publisher, Publisher.class));
    }

    @Transaction
    public PublisherDto testMethod() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setPublisherName("publisherName");
        return modelMapper.map(publisherRepository.getById(publisher), PublisherDto.class);
    }
}
