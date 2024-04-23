package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.CartMapper;
import eu.senla.naumovich.services.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartDto> getAll() {
        try {
            List<Cart> carts = cartRepository.getAll();
            List<CartDto> cartsDto = carts.stream()
                    .map(cartMapper::toDto)
                    .collect(Collectors.toList());
            return cartsDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public CartDto getById(Long id) {
        try {
            return cartMapper.toDto(cartRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public CartDto update(CartDto cart) {
        try {
            return cartMapper.toDto(cartRepository.update(cartMapper.toEntity(cart)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + cart.getId());
        }
    }

    @Override
    public void create(CartDto cart) {
        try {
            cartRepository.create(cartMapper.toEntity(cart));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Cart cart = cartRepository.getById(id);
            cartRepository.delete(cart);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
