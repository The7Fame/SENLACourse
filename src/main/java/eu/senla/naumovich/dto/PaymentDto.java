package eu.senla.naumovich.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {
    Long id;
    Boolean status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date paymentDate;
    Double totalPrice;
    UserDto user;
    OrderDto order;
}
