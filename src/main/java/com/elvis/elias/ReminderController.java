package com.elvis.elias;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReminderController {
    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;

    }

    @GetMapping("/api/reminders")
    public List<Reminder> getReminders() {
        return reminderService.getReminders();
    }
}