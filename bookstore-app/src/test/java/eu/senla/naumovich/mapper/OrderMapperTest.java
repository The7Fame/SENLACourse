package eu.senla.naumovich.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.dto.order.view.View;
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
        List<OrderShortDto> ordersDto = OrderMapper.INSTANCE.toDtoList(orders);

        assertEquals(readFromFile(View.WithPayment.class, "/order/order.json"), mapper
                .writerWithView(View.WithPayment.class).writeValueAsString(ordersDto));
    }

    @Test
    void ordersToOrderShortDtoWithoutPaymentMapping() throws JsonProcessingException {
        List<Order> orders = List.of(Generator.createOrder());
        List<OrderShortDto> ordersDto = OrderMapper.INSTANCE.toDtoList(orders);

        assertEquals(readFromFile(View.WithoutPayment.class, "/order/orderWithoutPayment.json"), mapper
                .writerWithView(View.WithoutPayment.class).writeValueAsString(ordersDto));
    }

    private String readFromFile(Class<?> view, String fileName) {
        try {
            URL resource = getClass().getResource(fileName);
            String result = Files.readString(Paths.get(resource.toURI()));
            List<OrderShortDto> ordersFromFile = mapper.readValue(result,
                    mapper.writerWithView(view)
                            .getTypeFactory().constructCollectionType(List.class, OrderShortDto.class));

            return mapper.writeValueAsString(ordersFromFile);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
