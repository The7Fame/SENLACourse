package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.config.WithMockCustomUser;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.review.ReviewCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReviewControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/review"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/review/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/review/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    @WithMockCustomUser
    public void createUserReviewToBook() throws Exception {
        ReviewCreateDto reviewCreateDto = Generator.reviewCreateDto();
        mockMvc.perform(post("/review/book/1").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reviewCreateDto)))
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    @WithMockCustomUser
    public void deleteUserReviewToBook() throws Exception {
        mockMvc.perform(delete("/review/1/book"))
                .andExpect(status().isNoContent());
    }
}
