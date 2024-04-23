package eu.senla.naumovich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.controller.common.BaseTest;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.PrivilegeDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PrivilegeControllerTest extends BaseTest {

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/privilege"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getBeIdTest() throws Exception {
        mockMvc.perform(get("/privilege/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void getUpdateTest() throws Exception {
        PrivilegeDto privilegeDto = Generator.updatePrivilegeDto();
        mockMvc.perform(put("/privilege")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(privilegeDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        mockMvc.perform(post("/privilege")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(privilegeDto)))
                .andExpect(status().isCreated());
    }
}
