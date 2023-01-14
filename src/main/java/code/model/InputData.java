package code.model;

import code.model.enumes.FIT_STRATEGY;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class InputData {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Lesson> lessonsList;
    private int sizeOfPopulation = 100;
    private int percent = 10;
    private FIT_STRATEGY fit_strategy = FIT_STRATEGY.WINDOWS;

    public InputData(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    public List<GroupTeacherSubject> getDataToTimetable() {
        List<GroupTeacherSubject> dataToTimetable = new ArrayList<>();
        for (Lesson lesson : lessonsList)
            dataToTimetable.add(new GroupTeacherSubject(lesson.getGroup(), lesson.getTeacher(), lesson.getSubject()));
        return dataToTimetable;
    }
}
