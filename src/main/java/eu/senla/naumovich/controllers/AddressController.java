package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.dto.address.AddressShortDto;
import eu.senla.naumovich.services.service.AddressService;
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
@RequestMapping("/address")
public class AddressController implements CRUDInterface<AddressDto, AddressShortDto> {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get addresses");
        List<AddressShortDto> addressesDto = addressService.getAll(page, size, sort);
        return ResponseEntity.ok(addressesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get address by id: {}", id);
        AddressDto addressDto = addressService.getById(id);
        return ResponseEntity.ok(addressDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
    public ResponseEntity<AddressDto> update(@Valid @RequestBody AddressDto addressDto) {
        log.info("An attempt to update address");
        AddressDto address = addressService.update(addressDto);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ADDRESS')")
    public ResponseEntity<AddressDto> create(@Valid @RequestBody AddressDto addressDto) {
        log.info("An attempt to create address");
        AddressDto address = addressService.create(addressDto);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADDRESS')")
    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {
        log.info("An attempt to delete address by id {}", id);
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
