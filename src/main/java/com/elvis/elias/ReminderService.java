package com.elvis.elias;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//@Service tells spring to manage an instance of this class so we can supply it to our controller later
@Service
public class ReminderService {
    // a list that holds Reminder objects
    // final prevents replaces this field with a different list
    private final List<Reminder> reminders = new ArrayList<>();
    private long nextId = 1;

    // is the return type ie a list of reminder objects
    public synchronized List<Reminder> getReminders() {
        // returns an unmodifiable copy of the lsit. other code can only read it
        return List.copyOf(reminders);
    }

    // synchronized acquire this object’s lock before running this method, makes
    // sure requests dont overlap with the Id numbers
    public synchronized Reminder createReminder(String title, OffsetDateTime remindAt) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank"); // thorw stops this method and reports the
                                                                           // invalid input
        }
        if (remindAt == null || !remindAt.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Reminder time must be in the future");
        }
        Reminder reminder = new Reminder(nextId, title, remindAt);
        reminders.add(reminder);
        nextId++;

        return reminder;
    }
}