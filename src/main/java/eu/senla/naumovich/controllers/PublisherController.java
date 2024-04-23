package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.services.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController implements CRUDInterface<PublisherDto> {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll() {
        List<PublisherDto> publisherDto = publisherService.getAll();
        return ResponseEntity.ok(publisherDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Long id) {
        PublisherDto publisherDto = publisherService.getById(id);
        if (publisherDto == null) {
            return ResponseEntity.notFound().build();
        }
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
