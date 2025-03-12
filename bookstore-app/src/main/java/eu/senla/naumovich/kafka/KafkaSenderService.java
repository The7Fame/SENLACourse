package eu.senla.naumovich.kafka;

import eu.senla.naumovich.dto.address.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaSenderService {
    @Value("${kafka.topic.address-update}")
    private String addressUpdateTopic;

    private final KafkaTemplate<Long, AddressDto> kafkaTemplate;

    public void sendAddress(AddressDto address) {
        log.info("Sending updated address = {} ", address);
        CompletableFuture<SendResult<Long, AddressDto>> result = kafkaTemplate.send(addressUpdateTopic, address.getId(), address);

        result.thenAccept(res -> log.info("Address send successfully"));
    }
}
