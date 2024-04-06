package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.GenreRepository;
import eu.senla.naumovich.dto.GenreDto;
import eu.senla.naumovich.entities.Genre;
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
    private final ModelMapper modelMapper;

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.getAll();
        List<GenreDto> genresDto = genres.stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toList());
        return genresDto;
    }

    @Override
    public GenreDto getById(GenreDto genre) {
        return modelMapper.map(genreRepository.getById(modelMapper.map(genre, Genre.class)), GenreDto.class);
    }

    @Override
    public GenreDto update(GenreDto genre) {
        return modelMapper.map(genreRepository.update(modelMapper.map(genre, Genre.class)), GenreDto.class);
    }

    @Override
    public GenreDto create(GenreDto genre) {
        return modelMapper.map(genreRepository.create(modelMapper.map(genre, Genre.class)), GenreDto.class);
    }

    @Override
    public void delete(GenreDto genre) {
        genreRepository.delete(modelMapper.map(genre, Genre.class));
    }
}
