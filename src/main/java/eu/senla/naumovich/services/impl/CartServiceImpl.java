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

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
        private final CartRepository cartRepository;
        private final CartMapper cartMapper;

        @Override
        public List<CartDto> getAll(int size, int page) {
                List<Cart> carts = cartRepository.getAll(size, page);
                return cartMapper.toDtoList(carts);
        }

        @Override
        public CartDto getById(Long id) {
                return cartMapper.toDto(cartRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));
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
        public void delete(Long id) {
                cartRepository.deleteById(id);
        }
}
