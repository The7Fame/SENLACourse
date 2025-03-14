package eu.senla.naumovich.kafka;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.address.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaSenderServiceTest {
    @Mock
    private KafkaTemplate<Long, AddressDto> kafkaTemplate;
    @InjectMocks
    private KafkaSenderService kafkaSenderService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(kafkaSenderService, "addressUpdateTopic", "address.update");
    }

    @Test
    public void testSendAddress() {
        AddressDto address = Generator.createAddressDto();
        CompletableFuture<SendResult<Long, AddressDto>> future = new CompletableFuture<>();
        when(kafkaTemplate.send("address.update", address.getId(), address)).thenReturn(future);
        kafkaSenderService.sendAddress(address);
        verify(kafkaTemplate).send("address.update", address.getId(), address);
    }
}
