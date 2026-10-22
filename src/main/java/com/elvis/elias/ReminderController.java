package com.elvis.elias;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @PostMapping("/api/reminders")
    @ResponseStatus(HttpStatus.CREATED)
    public Reminder createReminder(@RequestBody CreateReminderRequest request) {
        return reminderService.createReminder(
                request.title(),
                request.remindAt());
    }
}