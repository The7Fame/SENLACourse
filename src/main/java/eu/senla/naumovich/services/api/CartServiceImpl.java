package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.services.mapper.CartMapper;
import eu.senla.naumovich.services.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.getAll();
        List<CartDto> cartsDto = carts.stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
        return cartsDto;
    }

    @Override
    public CartDto getById(CartDto cart) {
        return cartMapper.toDto(cartRepository.getById(cartMapper.toEntity(cart)));
    }

    @Override
    public CartDto update(CartDto cart) {
        return cartMapper.toDto(cartRepository.update(cartMapper.toEntity(cart)));
    }

    @Override
    public CartDto create(CartDto cart) {
        return cartMapper.toDto(cartRepository.create(cartMapper.toEntity(cart)));
    }

    @Override
    public void delete(CartDto cart) {
        cartRepository.delete(cartMapper.toEntity(cart));
    }
}
