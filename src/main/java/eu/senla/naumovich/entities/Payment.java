package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    Long id;
    Boolean status;
    Date paymentDate;
    Double totalPrice;
    User user;
    Order order;
}
