package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.services.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController implements CRUDInterface<OrderDto> {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orderDto = orderService.getAll();
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long id) {
        OrderDto orderDto = orderService.getById(id);
        if (orderDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto) {
        orderService.update(orderDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) {
        orderService.create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
