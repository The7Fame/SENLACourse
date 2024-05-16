package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.publisher.PublisherDto;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController implements CRUDInterface<PublisherDto> {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        List<PublisherDto> publisherDto = publisherService.getAll(page, size);
        return ResponseEntity.ok(publisherDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Long id) {
        PublisherDto publisherDto = publisherService.getById(id);
        return ResponseEntity.ok(publisherDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> update(@RequestBody PublisherDto publisherDto) {
        publisherService.update(publisherDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> create(@RequestBody PublisherDto publisherDto) {
        publisherService.create(publisherDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
