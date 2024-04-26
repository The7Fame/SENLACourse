package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController implements CRUDInterface<PublisherDto> {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll() {
        List<PublisherDto> publisherDto = publisherService.getAll();
        return ResponseEntity.ok(publisherDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Long id) {
        PublisherDto publisherDto = publisherService.getById(id);
        return ResponseEntity.ok(publisherDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PublisherDto publisherDto) {
        publisherService.update(publisherDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PublisherDto publisherDto) {
        publisherService.create(publisherDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
