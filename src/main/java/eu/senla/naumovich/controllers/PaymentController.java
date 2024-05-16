package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.exceptions.NoMoneyOnBankAccount;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.PaymentService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController implements CRUDInterface<PaymentDto> {
    private final PaymentService paymentService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PaymentDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        List<PaymentDto> paymentDto = paymentService.getAll(page, size);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_PAYMENT')")
    public ResponseEntity<PaymentDto> getById(@PathVariable("id") Long id) {
        PaymentDto paymentDto = paymentService.getById(id);
        return ResponseEntity.ok(paymentDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> update(@RequestBody PaymentDto paymentDto) {
        PaymentDto paymentUpdated = paymentService.update(paymentDto);
        return new ResponseEntity<>(paymentUpdated, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> create(@RequestBody PaymentDto paymentDto) {
        paymentService.create(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createPayment(@AuthenticationPrincipal SecurityUser securityUser, @RequestBody OrderCreateDto orderCreateDto) {
        PaymentDto paymentDto = paymentService.createPayment(securityUser, orderCreateDto);
        return new ResponseEntity<>(paymentDto, HttpStatus.CREATED);
    }
}
