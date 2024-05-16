package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController implements CRUDInterface<AddressDto> {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        List<AddressDto> addressesDto = addressService.getAll(page, size);
        return ResponseEntity.ok(addressesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable("id") Long id) {
        AddressDto addressDto = addressService.getById(id);
        return ResponseEntity.ok(addressDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto addressDto) {
        addressService.update(addressDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
        AddressDto address = addressService.create(addressDto);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
