package com.elvis.elias;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReminderController.class)
@Import({ ReminderService.class, ApiExceptionHandler.class })
class ReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void blankTitleReturnsBadRequest() throws Exception {
        String requestBody = """
                {
                    "title": "   ",
                    "remindAt": "%s"
                }
                """.formatted(OffsetDateTime.now().plusDays(1));

        mockMvc.perform(post("/api/reminders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error")
                        .value("Title must not be blank"));
    }

    @Test
    void validReminderReturnsCreated() throws Exception {
        String requestBody = """
                {
                    "title": "Call the dentist",
                    "remindAt": "%s"
                }
                """.formatted(OffsetDateTime.now().plusDays(1));

        mockMvc.perform(post("/api/reminders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Call the dentist"))
                .andExpect(jsonPath("$.remindAt").isNotEmpty());
    }
}