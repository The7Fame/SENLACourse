package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseTest {

    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username="user1", authorities={"ADMIN"})
    public void getBeIdTest() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockUser(username="user1", authorities={"USER"})
    public void getUpdateTest() throws Exception {
        UserDto userDto = Generator.updateUserDto();
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="user1", authorities={"USER"})
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());
    }
}

