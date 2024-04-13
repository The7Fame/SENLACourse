package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryImpl extends AbstractDao<Long, Cart> implements CartRepository {
    @Override
    protected Class<Cart> getEntityClass() {
        return Cart.class;
    }
}
