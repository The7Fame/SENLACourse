package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.services.mapper.PublisherMapper;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publishers = publisherRepository.getAll();
        List<PublisherDto> publishersDto = publishers.stream().map(publisherMapper::toDto).collect(Collectors.toList());
        return publishersDto;
    }

    @Override
    public PublisherDto getById(PublisherDto publisher) {
        return publisherMapper.toDto(publisherRepository.getById(publisher.getId()));
    }

    @Override
    public PublisherDto update(PublisherDto publisher) {
        return publisherMapper.toDto(publisherRepository.update(publisherMapper.toEntity(publisher)));
    }

    @Override
    public void create(PublisherDto publisher) {
        publisherRepository.create(publisherMapper.toEntity(publisher));
    }

    @Override
    public void delete(PublisherDto publisher) {
        publisherRepository.delete(publisherMapper.toEntity(publisher));
    }
}
