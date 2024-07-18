package eu.senla.naumovich.dto.user;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
public class UserReplenishBalanceDto {
    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "1.00", message = "Balance must be greater than 1")
    private BigDecimal balance;
}
