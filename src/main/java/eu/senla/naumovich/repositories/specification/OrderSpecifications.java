package eu.senla.naumovich.repositories.specification;

import eu.senla.naumovich.entities.Order;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrderSpecifications {
    public static Specification<Order> totalPriceGreaterThan(BigDecimal totalPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("totalPrice"), totalPrice));
    }
}
