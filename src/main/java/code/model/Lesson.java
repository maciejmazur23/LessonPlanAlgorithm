package code.model;

import code.model.enumes.*;
import lombok.Data;

@Data
public class Lesson {
    private final DAYS day;
    private final HOUR hour;
    private final GROUP group;
    private final TEACHER teacher;
    private final SUBJECT subject;
    private final ROOM room;
}
