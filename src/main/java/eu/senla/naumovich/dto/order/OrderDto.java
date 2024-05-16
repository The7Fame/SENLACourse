package eu.senla.naumovich.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

import eu.senla.naumovich.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class OrderDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date orderDate;
    private BigDecimal totalPrice;
    private UserDto user;
}
