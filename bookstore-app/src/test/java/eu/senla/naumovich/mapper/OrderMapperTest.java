package eu.senla.naumovich.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.dto.order.OrderWithPaymentDto;
import eu.senla.naumovich.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void ordersToOrderShortDtoMapping() throws JsonProcessingException {
        List<Order> orders = List.of(Generator.createOrder());
        List<OrderWithPaymentDto> ordersDto = OrderMapper.INSTANCE.toOrderWithPaymentDtoList(orders);

        assertEquals(readFromFile("/order/order.json"), mapper.writeValueAsString(ordersDto));
    }

    private String readFromFile(String fileName) {
        try {
            URL resource = getClass().getResource(fileName);
            String result = Files.readString(Paths.get(resource.toURI()));
            List<OrderShortDto> ordersFromFile = mapper.readValue(result,
                    mapper.getTypeFactory().constructCollectionType(List.class, OrderWithPaymentDto.class));

            return mapper.writeValueAsString(ordersFromFile);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
