package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> getAll();

    PublisherDto getById(PublisherDto publisher);

    PublisherDto update(PublisherDto publisher);

    PublisherDto create(PublisherDto publisher);

    void delete(PublisherDto publisher);
}
