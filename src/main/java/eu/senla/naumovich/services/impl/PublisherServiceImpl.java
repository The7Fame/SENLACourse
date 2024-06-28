package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.publisher.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.PublisherMapper;
import eu.senla.naumovich.repositories.PublisherRepository;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
        private final PublisherRepository publisherRepository;

        private final PublisherMapper publisherMapper;

        @Override
        public List<PublisherDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Publisher> publisherPage = publisherRepository.findAll(pageable);
                return publisherMapper.toDtoList(publisherPage.getContent());
        }

        @Override
        public PublisherDto getById(Long id) {
                return publisherMapper.toDto(publisherRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
        }

        @Override
        public PublisherDto update(PublisherDto publisher) {
                return publisherMapper.toDto(publisherRepository.save(publisherMapper.toEntity(publisher)));

        }

        @Override
        public PublisherDto create(PublisherDto publisher) {
                try {
                        return publisherMapper.toDto(publisherRepository.save(publisherMapper.toEntity(publisher)));
                }catch (Exception e){
                        throw new RecordExistsException("Record is exists");
                }
        }

        @Override
        public void delete(Long id) {
                publisherRepository.deleteById(id);
        }
}
