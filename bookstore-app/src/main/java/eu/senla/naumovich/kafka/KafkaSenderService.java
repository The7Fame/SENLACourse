package eu.senla.naumovich.kafka;

import eu.senla.naumovich.dto.address.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSenderService {
    @Value("${kafka.topic.address-update}")
    private String addressUpdateTopic;

    private final KafkaTemplate<Long, AddressDto> kafkaTemplate;

    public void sendAddress(AddressDto address) {
        log.info("Sending updated address = {} to topic = {} with key = {}", address, addressUpdateTopic, address.getId());
        CompletableFuture<SendResult<Long, AddressDto>> result = kafkaTemplate.send(addressUpdateTopic, address.getId(), address);

        result.thenAccept(res -> {
            log.info("Address sent successfully to topic = {} with key = {}", addressUpdateTopic, address.getId());
        }).exceptionally(ex -> {
            log.error("Failed to send address to topic = {} with key = {}. Exception: {}", addressUpdateTopic, address.getId(), ex.getMessage(), ex);
            return null;
        });
    }
}
