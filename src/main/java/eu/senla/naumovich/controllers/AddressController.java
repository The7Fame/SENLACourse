package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.services.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController implements CRUDInterface<AddressDto> {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        List<AddressDto> addressesDto = addressService.getAll();
        return ResponseEntity.ok(addressesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable("id") Long id) {
        AddressDto addressDto = addressService.getById(id);
        if (addressDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressDto);
    }

    @PutMapping
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto addressDto) {
        addressService.update(addressDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AddressDto addressDto) {
        addressService.create(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
