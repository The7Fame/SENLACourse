package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto paymentDto);
}
