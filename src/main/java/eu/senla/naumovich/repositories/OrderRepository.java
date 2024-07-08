package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.repositories.custom.OrderDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderDao {
    @Query("select o from Order o where o.user.id = :userId")
    Page<Order> getOrdersByUserId(@Param("userId") long userId, Pageable pageable);
}
