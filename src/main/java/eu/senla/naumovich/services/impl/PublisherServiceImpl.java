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

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
        private final PublisherRepository publisherRepository;
        private final PublisherMapper publisherMapper;

        @Override
        public List<PublisherDto> getAll(int size, int page) {
                List<Publisher> publishers = publisherRepository.getAll(size, page);
                return publisherMapper.toDtoList(publishers);
        }

        @Override
        public PublisherDto getById(Long id) {

                return publisherMapper.toDto(publisherRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));

        }

        @Override
        public PublisherDto update(PublisherDto publisher) {

                return publisherMapper.toDto(publisherRepository.update(publisherMapper.toEntity(publisher)));

        }

        @Override
        public PublisherDto create(PublisherDto publisher) {
                return publisherMapper.toDto(publisherRepository.create(publisherMapper.toEntity(publisher)));

        }

        @Override
        public void delete(Long id) {
                publisherRepository.deleteById(id);

        }
}
