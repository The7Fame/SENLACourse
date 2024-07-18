package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.entities.enums.Genre;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.PromotionMapper;
import eu.senla.naumovich.repositories.BookRepository;
import eu.senla.naumovich.repositories.PromotionRepository;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final BookRepository bookRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public List<PromotionShortDto> getAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        Page<Promotion> promotionPage = promotionRepository.findAll(pageable);
        return promotionMapper.toDtoList(promotionPage.getContent());
    }

    @Override
    public PromotionDto getById(Long id) {
        return promotionMapper.toDto(
                promotionRepository.findById(id).orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
    }

    @Override
    public PromotionDto update(PromotionDto promotion) {
        return promotionMapper.toDto(promotionRepository.save(promotionMapper.toEntity(promotion)));
    }

    @Override
    public PromotionDto create(PromotionDto promotion) {
        try {
            return promotionMapper.toDto(promotionRepository.save(promotionMapper.toEntity(promotion)));
        }catch (Exception e){
            throw new RecordExistsException("Record is exists");
        }
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public PromotionDto createPromotionByGenre(CreatePromotionGenreDto promotionDto) {
        Genre genre = Genre.lookup(promotionDto.getGenreId());
        List<Book> books = bookRepository.getBookByGenre(genre);
        try {
            return promotionMapper.toDto(promotionRepository
                    .save(Promotion.builder()
                            .books(books)
                            .percent(promotionDto.getPercent())
                            .promotionName(promotionDto.getPromotionName())
                            .build()));
        }catch (Exception e) {
            throw new RecordExistsException("Record is exists");
        }
    }

    @Override
    public PromotionDto createPromotionByAuthor(CreatePromotionAuthorDto promotionDto) {
        List<Book> books = bookRepository.getBooksByAuthor(promotionDto.getAuthorName());
        try {
            return promotionMapper.toDto(promotionRepository
                    .save(Promotion.builder()
                            .books(books)
                            .percent(promotionDto.getPercent())
                            .promotionName(promotionDto.getPromotionName())
                            .build()));
        }catch (Exception e) {
            throw new RecordExistsException("Record is exists");
        }
    }
}
