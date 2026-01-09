
package dev.felix.elifoot.Controller;

import dev.felix.elifoot.BaseIntegrationTest;
import dev.felix.elifoot.Controller.Request.StadiumRequest;
import dev.felix.elifoot.Entity.Stadium;
import dev.felix.elifoot.REpository.StadiumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StadiumControlerTest extends BaseIntegrationTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    StadiumRepository repository;

    @BeforeEach
    void setup() {
        repository.save(Stadium.builder()
                .name("Old Stadium")
                .city("Old City")
                .capacity(5000)
                .urlImg("http://example.com/old_stadium.png")
                .build());
    }

    @WithMockUser(authorities = "SCOPE_stadium:write")
    @Test
    @DisplayName("Should create a new stadium")
    void shouldCreateNewStadium() throws Exception {

        StadiumRequest request = StadiumRequest.builder()
                .capacity(1001)
                .city("City A")
                .name("Stadium A")
                .urlImg("http://example.com/stadiumA.png")
                .build();

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Stadium A"))
                .andExpect(jsonPath("$.city").value("City A"))
                .andExpect(jsonPath("$.capacity").value(1001))
                .andExpect(jsonPath("$.urlImg").value("http://example.com/stadiumA.png"));
    }

    @WithMockUser(authorities = "SCOPE_stadium:read")
    @Test
    @DisplayName("Deve listar os estádios")
    void deveListarEstadios() throws Exception {
        mockMvc.perform(get("/stadiums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").exists());

    }
}
