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


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GenreDto> getAll() {
        List<GenreDto> genresDto = new ArrayList<>();
        List<Genre> genres = genreRepository.getAll();
        for(Genre genre : genres){
            genresDto.add(modelMapper.map(genre, GenreDto.class));
        }
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
