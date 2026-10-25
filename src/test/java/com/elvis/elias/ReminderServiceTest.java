package com.elvis.elias;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    @Test
    void concurrentCreationStoresRemindersWithUniqueIds() throws Exception {
        ReminderService service = new ReminderService();
        OffsetDateTime futureTime = OffsetDateTime.now().plusDays(1);

        ExecutorService workers = Executors.newFixedThreadPool(4);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<Reminder>> results = new ArrayList<>();

        try {
            for (int i = 0; i < 20; i++) {
                results.add(workers.submit(() -> {
                    start.await();
                    return service.createReminder("Test reminder", futureTime);
                }));
            }

            start.countDown();

            Set<Long> uniqueIds = new HashSet<>();

            for (Future<Reminder> result : results) {
                Reminder reminder = result.get(5, TimeUnit.SECONDS);
                uniqueIds.add(reminder.getId());
            }

            assertEquals(20, service.getReminders().size());
            assertEquals(20, uniqueIds.size());
        } finally {
            workers.shutdownNow();
        }
    }
}