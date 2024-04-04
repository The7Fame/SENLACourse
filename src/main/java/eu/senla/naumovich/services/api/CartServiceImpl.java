package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.entities.Cart;
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
    private final ModelMapper modelMapper;

    @Override
    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.getAll();
        List<CartDto> cartsDto = carts.stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());
        return cartsDto;
    }

    @Override
    public CartDto getById(CartDto cart) {
        return modelMapper.map(cartRepository.getById(modelMapper.map(cart, Cart.class)), CartDto.class);
    }

    @Override
    public CartDto update(CartDto cart) {
        return modelMapper.map(cartRepository.update(modelMapper.map(cart, Cart.class)), CartDto.class);
    }

    @Override
    public CartDto create(CartDto cart) {
        return modelMapper.map(cartRepository.create(modelMapper.map(cart, Cart.class)), CartDto.class);
    }

    @Override
    public void delete(CartDto cart) {
        cartRepository.delete(modelMapper.map(cart, Cart.class));
    }
}
