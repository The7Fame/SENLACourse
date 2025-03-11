package eu.senla.naumovich.dto.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
public class PaymentForOrderDto {
    private Boolean status;
    private BigDecimal totalPrice;
}
