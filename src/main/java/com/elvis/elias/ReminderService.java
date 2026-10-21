package com.elvis.elias;

import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

//@Service tells spring to manage an instance of this class so we can supply it to our controller later
@Service
public class ReminderService {
    // a list that holds Reminder objects
    // final prevents replaces this field with a different list
    private final List<Reminder> reminders = new ArrayList<>();
    private long nextId = 1;

    // is the return type ie a list of reminder objects
    public List<Reminder> getReminders() {
        // returns an unmodifiable copy of the lsit. other code can only read it
        return List.copyOf(reminders);
    }

    public Reminder createReminder(String title, OffsetDateTime remindAt) {
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