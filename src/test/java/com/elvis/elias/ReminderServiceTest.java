package com.elvis.elias;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReminderServiceTest {

    @Test
    void rejectsPastTimeWithoutStoringReminder() {
        ReminderService service = new ReminderService();
        OffsetDateTime pastTime = OffsetDateTime.now().minusDays(1);
        
        // checks that creating a past reminder throws the expect
        // exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.createReminder("Call the dentist", pastTime); //gives JUnit an action to run and check this is called a lambda
        });

        assertTrue(service.getReminders().isEmpty()); // checks that the list is still empty afterward
    }
}