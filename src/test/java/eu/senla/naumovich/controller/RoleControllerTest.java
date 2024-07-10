package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.role.RoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoleControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void getAllTest() throws Exception {
        mockMvc.perform(get("/role"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/role/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void createTest() throws Exception {
        RoleDto roleDto = Generator.createRoleDto();
        mockMvc.perform(post("/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(roleDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void getUpdateTest() throws Exception {
        RoleDto roleDto = Generator.updateRoleDto();
        System.out.println(new ObjectMapper().writeValueAsString(roleDto));
        mockMvc.perform(put("/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(roleDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "ADMIN" })
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/role/1"))
                .andExpect(status().isNoContent());
    }
}
