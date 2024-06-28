package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.config.WithMockCustomUser;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.book.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getDeleteTest() throws Exception {
        mockMvc.perform(delete("/order/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockCustomUser
    public void getUserOrders() throws Exception {
        mockMvc.perform(get("/order/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockCustomUser
    public void getUserOrder() throws Exception {
        mockMvc.perform(get("/order/my/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockCustomUser
    public void getCreateTest() throws Exception {
        BookDto book = Generator.createBookDto();
        List<BookDto> books = Collections.singletonList(book);
        mockMvc.perform(post("/order/my")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(books)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockCustomUser
    public void getDeleteUserOrder() throws Exception {
        mockMvc.perform(delete("/order/my/1"))
                .andExpect(status().isNoContent());
    }
}
