package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.publisher.PublisherDto;
import eu.senla.naumovich.services.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController implements CRUDInterface<PublisherDto, PublisherDto> {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get publishers");
        List<PublisherDto> publisherDto = publisherService.getAll(page, size, sort);
        return ResponseEntity.ok(publisherDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get publisher by id {}", id);
        PublisherDto publisherDto = publisherService.getById(id);
        return ResponseEntity.ok(publisherDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> update(@Valid @RequestBody PublisherDto publisherDto) {
        log.info("An attempt to update publisher");
        PublisherDto publisher = publisherService.update(publisherDto);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> create(@Valid @RequestBody PublisherDto publisherDto) {
        log.info("An attempt to create publisher");
        PublisherDto publisher = publisherService.create(publisherDto);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete publisher by id {}", id);
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
