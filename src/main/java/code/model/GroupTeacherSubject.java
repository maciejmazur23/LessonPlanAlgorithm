package code.model;

import code.model.enumes.GROUP;
import code.model.enumes.SUBJECT;
import code.model.enumes.TEACHER;

public record GroupTeacherSubject(
        GROUP group,
        TEACHER teacher,
        SUBJECT subject
) {
}
