package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.book.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    void getAllTest() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "CREATE_BOOK" })
    void getCreateTest() throws Exception {
        BookDto bookDto = Generator.createBookDto();
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "UPDATE_BOOK" })
    void getUpdateTest() throws Exception {
        BookDto bookDto = Generator.updateBookDto();
        mockMvc.perform(put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "DELETE_BOOK" })
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    void getBookReviewsByIdTest() throws Exception {
        mockMvc.perform(get("/book/1/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    void getSearchBookByTitleAndGenreTest() throws Exception {
        mockMvc.perform(get("/book/search?genre=1&title=Eve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    void getPopularBooksTest() throws Exception {
        mockMvc.perform(get("/book/popular"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
