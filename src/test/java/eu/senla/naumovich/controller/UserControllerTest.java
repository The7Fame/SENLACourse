package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.config.WithMockCustomUser;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "DELETE_USER" })
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockCustomUser
    public void getUserProfileTest() throws Exception {
        mockMvc.perform(get("/user/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @WithMockCustomUser
    public void replenishBalanceUserTest() throws Exception {
        UserReplenishBalanceDto balanceDto = Generator.userReplenishBalanceDto();
        mockMvc.perform(post("/user/my/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(balanceDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockCustomUser
    public void tryToUpdateUserTest() throws Exception {
        UserUpdateDto userUpdateDto = Generator.userUpdateDto();
        mockMvc.perform(put("/user/my")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userUpdateDto)))
                .andExpect(status().isCreated());
    }
}
