package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.author.AuthorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/author/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "UPDATE_AUTHOR" })
    public void getUpdateTest() throws Exception {
        AuthorDto authorDto = Generator.updateAuthorDto();
        mockMvc.perform(put("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authorDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "CREATE_AUTHOR" })
    public void createTest() throws Exception {
        AuthorDto authorDto = Generator.createAuthorDto();
        System.out.println(new ObjectMapper().writeValueAsString(authorDto));
        mockMvc.perform(post("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authorDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getAuthorBooksTest() throws Exception {
        mockMvc.perform(get("/author?name=Boris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getUnknownAuthorBooksTest() throws Exception {
        mockMvc.perform(get("/author/search?name=Unknown"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void userTryUpdateTest() throws Exception {
        AuthorDto authorDto = Generator.updateAuthorDto();
        mockMvc.perform(put("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authorDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void userTryCreateTest() throws Exception {
        AuthorDto authorDto = Generator.createAuthorDto();
        System.out.println(new ObjectMapper().writeValueAsString(authorDto));
        mockMvc.perform(post("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authorDto)))
                .andExpect(status().isForbidden());
    }
}
