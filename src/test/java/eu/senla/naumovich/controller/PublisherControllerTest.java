package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.publisher.PublisherDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PublisherControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/publisher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/publisher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "MANAGER" })
    public void getUpdateTest() throws Exception {
        PublisherDto publisherDto = Generator.updatePublisherDto();
        mockMvc.perform(put("/publisher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(publisherDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/publisher/1"))
                .andExpect(status().isNoContent());
    }
}
