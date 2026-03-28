package com.harrypotter;

import com.harrypotter.controllers.MagicObjectsController;
import com.harrypotter.exceptions.GlobalExceptionHandler;
import com.harrypotter.exceptions.ResourceNotFoundException;
import com.harrypotter.services.MagicObjectsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private MagicObjectsService magicObjectsService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MagicObjectsController controller = new MagicObjectsController();
        ReflectionTestUtils.setField(controller, "magicObjectsService", magicObjectsService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnValidationErrorsForInvalidMagicObjectPayload() throws Exception {
        String requestBody = """
                {
                  \"name\": \"\",
                  \"info\": \"\",
                  \"picture\": \"\",
                  \"isHorocruxe\": null,
                  \"isDeathHollow\": null,
                  \"isWander\": null,
                  \"isOtherMagicObject\": null,
                  \"isQuiddich\": null
                }
                """;

        mockMvc.perform(post("/objects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details.name").value("name is required"))
                .andExpect(jsonPath("$.details.info").value("info is required"))
                .andExpect(jsonPath("$.details.picture").value("picture is required"));
    }

    @Test
    void shouldReturnNotFoundResponseWhenResourceDoesNotExist() throws Exception {
        when(magicObjectsService.findByName("missing"))
                .thenThrow(new ResourceNotFoundException("Magic object not found with name: missing"));

        mockMvc.perform(get("/objects/missing"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Magic object not found with name: missing"))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}

