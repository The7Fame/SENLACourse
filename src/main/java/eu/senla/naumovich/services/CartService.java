package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAll();

    CartDto getById(CartDto cart);

    CartDto update(CartDto cart);

    CartDto create(CartDto cart);

    void delete(CartDto cart);
}
