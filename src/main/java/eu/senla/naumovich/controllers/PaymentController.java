package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.services.service.PaymentService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController implements CRUDInterface<PaymentDto> {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll() {
        List<PaymentDto> paymentDto = paymentService.getAll();
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable("id") Long id) {
        PaymentDto paymentDto = paymentService.getById(id);
        return ResponseEntity.ok(paymentDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PaymentDto paymentDto) {
        paymentService.update(paymentDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentDto paymentDto) {
        paymentService.create(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
