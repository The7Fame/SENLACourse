package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.order.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends BaseTest {

    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    public void getBeIdTest() throws Exception {
        mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username="user1", authorities={"USER"})
    public void getUpdateTest() throws Exception {
        OrderDto orderDto = Generator.updateOrderDto();
        mockMvc.perform(put("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="user1", authorities={"USER"})
    public void createTest() throws Exception {
        OrderDto orderDto = Generator.createOrderDto();
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isCreated());
    }
}
