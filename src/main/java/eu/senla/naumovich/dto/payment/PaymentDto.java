package eu.senla.naumovich.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PaymentDto {
    private Long id;
    private Boolean status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date paymentDate;
    private BigDecimal totalPrice;
    private UserDto user;
    private OrderDto order;
}
