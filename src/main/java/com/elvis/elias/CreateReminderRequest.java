package com.elvis.elias;

import java.time.OffsetDateTime;

public record CreateReminderRequest( //a record is a java feature for holding data with less repatitive code
    String title,
    OffsetDateTime remindAt
) {}