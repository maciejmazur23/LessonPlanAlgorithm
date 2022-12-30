package code.model;

import code.model.enumes.FIT_STRATEGY;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class InputData {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Lesson> lessonsList;
    private int sizeOfPopulation = 100;
    private int percent = 10;
    private FIT_STRATEGY fit_strategy = FIT_STRATEGY.WINDOWS;

    public InputData(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    public void insert() {
        System.out.println("Insert size of population:");
        this.sizeOfPopulation = scanner.nextInt();

        System.out.println("Insert percentage that went to the new one:");
        this.percent = scanner.nextInt();

        System.out.println("Choice fitness calculator strategy:");
        System.out.println("1 -> "+FIT_STRATEGY.WINDOWS.getExplanation());
        System.out.println("2 -> "+FIT_STRATEGY.SIMILAR_NUMBER_OF_LESSONS.getExplanation());
        System.out.println("3 -> "+FIT_STRATEGY.LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY.getExplanation());

        switch (scanner.nextInt()){
            case 1 -> this.fit_strategy = FIT_STRATEGY.WINDOWS;
            case 2 -> this.fit_strategy = FIT_STRATEGY.SIMILAR_NUMBER_OF_LESSONS;
            case 3 -> this.fit_strategy = FIT_STRATEGY.LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY;
         }
    }

    public List<GroupTeacherSubject> getDataToTimetable() {
        List<GroupTeacherSubject> dataToTimetable = new ArrayList<>();
        for (Lesson lesson : lessonsList)
            dataToTimetable.add(new GroupTeacherSubject(lesson.getGroup(), lesson.getTeacher(), lesson.getSubject()));
        return dataToTimetable;
    }
}
