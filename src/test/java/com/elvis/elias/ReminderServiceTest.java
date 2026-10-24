package com.elvis.elias;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReminderServiceTest {

    @Test
    void rejectsPastTimeWithoutStoringReminder() {
        ReminderService service = new ReminderService();
        OffsetDateTime pastTime = OffsetDateTime.now().minusDays(1);

        // checks that creating a past reminder throws the expect
        // exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.createReminder("Call the dentist", pastTime); // gives JUnit an action to run and check this is
                                                                  // called a lambda
        });

        assertTrue(service.getReminders().isEmpty()); // checks that the list is still empty afterward
    }

    @Test
    void createsAndStoresValidReminder() {
        ReminderService service = new ReminderService();
        OffsetDateTime futureTime = OffsetDateTime.now().plusDays(1);

        Reminder reminder = service.createReminder("Call the dentist", futureTime);

        assertEquals(1L, reminder.getId());
        assertEquals("Call the dentist", reminder.getTitle());
        assertEquals(futureTime, reminder.getRemindAt());
        assertEquals(1, service.getReminders().size());
        assertEquals(reminder, service.getReminders().get(0));
    }

    @Test
    void rejectsBlankTitleWithoutStoringReminder() {
        ReminderService service = new ReminderService();
        OffsetDateTime futureTime = OffsetDateTime.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> { // gives JUnit an action to run and check this is
                                                             // called a lambda
            service.createReminder(" ", futureTime);
        });

        assertTrue(service.getReminders().isEmpty()); // checks that the list is still empty afterward
    }

    @Test
    void rejectsNullTitleWithoutStoringReminder() {
        ReminderService service = new ReminderService();
        OffsetDateTime futureTime = OffsetDateTime.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> { // gives JUnit an action to run and check this is
                                                             // called a lambda
            service.createReminder(null, futureTime);
        });
        assertTrue(service.getReminders().isEmpty());
    }

    @Test
    void rejectsNullTimeWithoutStoringReminder() {
        ReminderService service = new ReminderService();
       

        assertThrows(IllegalArgumentException.class, () -> { // gives JUnit an action to run and check this is
                                                             // called a lambda
            service.createReminder("Call the dentist", null);
        });
        assertTrue(service.getReminders().isEmpty());
    }
}