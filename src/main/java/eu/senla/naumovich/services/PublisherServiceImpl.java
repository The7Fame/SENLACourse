package eu.senla.naumovich.services;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PublisherDto> getAll() {
        List<PublisherDto> publishersDto = new ArrayList<>();
        List<Publisher> publishers = publisherRepository.getAll();
        for(Publisher publisher : publishers){
            publishersDto.add(modelMapper.map(publisher, PublisherDto.class));
        }
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
}
