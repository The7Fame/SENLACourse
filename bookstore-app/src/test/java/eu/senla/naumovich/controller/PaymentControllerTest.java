package eu.senla.naumovich.controller;

import eu.senla.naumovich.config.WithMockCustomUser;
import eu.senla.naumovich.controller.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest extends BaseTest {
    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    void getAllTest() throws Exception {
        mockMvc.perform(get("/payment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/payment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username="user1", authorities={"ADMIN"})
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/payment/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockCustomUser
    void getUserPayment() throws Exception {
        mockMvc.perform(get("/payment/my/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockCustomUser
    void tyTryPayPaidPaymentTest() throws Exception{
        String json = """
                    {
                        "id": 1
                    }
                """;
        mockMvc.perform(post("/payment/my")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
