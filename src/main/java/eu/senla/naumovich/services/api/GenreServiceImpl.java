package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.GenreRepository;
import eu.senla.naumovich.dto.GenreDto;
import eu.senla.naumovich.entities.Genre;
import eu.senla.naumovich.services.mapper.GenreMapper;
import eu.senla.naumovich.services.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.getAll();
        List<GenreDto> genresDto = genres.stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
        return genresDto;
    }

    @Override
    public GenreDto getById(GenreDto genre) {
        return genreMapper.toDto(genreRepository.getById(genreMapper.toEntity(genre)));
    }

    @Override
    public GenreDto update(GenreDto genre) {
        return genreMapper.toDto(genreRepository.update(genreMapper.toEntity(genre)));
    }

    @Override
    public GenreDto create(GenreDto genre) {
        return genreMapper.toDto(genreRepository.create(genreMapper.toEntity(genre)));
    }

    @Override
    public void delete(GenreDto genre) {
        genreRepository.delete(genreMapper.toEntity(genre));
    }
}
