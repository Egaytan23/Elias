package com.elvis.elias;

import java.time.OffsetDateTime;

public class Reminder {
    //create instance fields 
    private long id;
    private String title;
    private OffsetDateTime remindAt;
   

    //The constructor sets the starting values when we create a reminder object.
    public Reminder(long id, String title, OffsetDateTime remindAt) {
        this.id = id;
        this.title = title;
        this.remindAt = remindAt;

    }

    //getter retrieve the objects value 
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public OffsetDateTime getRemindAt() {
        return remindAt;
    }
}