package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.address.AddressDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddressControllerTest extends BaseTest {

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "UPDATE_ADDRESS" })
    public void getUpdateTest() throws Exception {
        AddressDto addressDto = Generator.updateAddressDto();

        mockMvc.perform(put("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "CREATE_ADDRESS" })
    public void createTest() throws Exception {
        AddressDto addressDto = Generator.createAddressDto();
        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "DELETE_ADDRESS" })
    @WithUserDetails("user1")
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/address/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void userTryUpdateTest() throws Exception {
        AddressDto addressDto = Generator.updateAddressDto();

        mockMvc.perform(put("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "USER" })
    public void userTryCreateTest() throws Exception {
        AddressDto addressDto = Generator.createAddressDto();
        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addressDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @WithMockUser(username = "user1", authorities = { "USER" })
    @WithUserDetails("user1")
    public void userTryDeleteTest() throws Exception {
        mockMvc.perform(delete("/address/1"))
                .andExpect(status().isForbidden());
    }
}
