package code.model;

import code.model.enumes.GROUP;
import code.model.enumes.SUBJECT;
import code.model.enumes.TEACHER;
import lombok.Data;

@Data
public class GroupTeacherSubject {
    private final GROUP group;
    private final TEACHER teacher;
    private final SUBJECT subject;
}
