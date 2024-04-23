package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PublisherMapper;
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
        try {
            List<Publisher> publishers = publisherRepository.getAll();
            List<PublisherDto> publishersDto = publishers.stream().map(publisherMapper::toDto)
                    .collect(Collectors.toList());
            return publishersDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public PublisherDto getById(Long id) {
        try {
            return publisherMapper.toDto(publisherRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public PublisherDto update(PublisherDto publisher) {
        try {
            return publisherMapper.toDto(publisherRepository.update(publisherMapper.toEntity(publisher)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + publisher.getId());
        }
    }

    @Override
    public void create(PublisherDto publisher) {
        try {
            publisherRepository.create(publisherMapper.toEntity(publisher));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Publisher publisher = publisherRepository.getById(id);
            publisherRepository.delete(publisher);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
