package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.payment.PaymentShortDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends InterfaceMapper<Payment, PaymentDto, PaymentShortDto> {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
}
