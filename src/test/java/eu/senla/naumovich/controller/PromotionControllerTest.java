package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PromotionControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void getAllTest() throws Exception {
        mockMvc.perform(get("/promotion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/promotion/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "UPDATE_PROMOTION" })
    void getUpdateTest() throws Exception {
        PromotionDto promotionDto = Generator.updatePromotionDto();
        mockMvc.perform(put("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(promotionDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "CREATE_PROMOTION" })
    void createTest() throws Exception {
        PromotionDto promotionDto = Generator.createPromotionDto();
        mockMvc.perform(post("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(promotionDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "DELETE_PROMOTION" })
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/promotion/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "CREATE_PROMOTION" })
    void createPromotionByGenre() throws Exception {
        CreatePromotionGenreDto createPromotionGenreDto = Generator.createPromotionGenreDto();
        System.out.println(createPromotionGenreDto);
        mockMvc.perform(post("/promotion/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createPromotionGenreDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "CREATE_PROMOTION" })
    void createPromotionByAuthorName() throws Exception {
        CreatePromotionAuthorDto createPromotionAuthorDto = Generator.createPromotionAuthorDto();
        mockMvc.perform(post("/promotion/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createPromotionAuthorDto)))
                .andExpect(status().isCreated());
    }
}
