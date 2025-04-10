package eu.senla.naumovich.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.senla.naumovich.dto.payment.PaymentForOrderDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@Jacksonized
public class OrderWithPaymentDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date orderDate;
    private BigDecimal totalPrice;
    private UserShortDto userInfo;
    private PaymentForOrderDto payment;
}
