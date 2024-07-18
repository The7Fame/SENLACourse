package eu.senla.naumovich.repositories.custom.impl;

import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.repositories.custom.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        String query = "select * from payments where order_id = ? ";
        List<Payment> payments = jdbcTemplate.query(
                query,
                new Object[]{orderId},
                new BeanPropertyRowMapper<>(Payment.class)
        );
        return payments.stream().findFirst();
    }
}
