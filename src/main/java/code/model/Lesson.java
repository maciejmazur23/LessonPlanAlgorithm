package code.model;

import code.model.enumes.*;

public record Lesson(
        DAYS day,
        HOUR hour,
        GROUP group,
        TEACHER teacher,
        SUBJECT subject,
        ROOM room
) {
}
