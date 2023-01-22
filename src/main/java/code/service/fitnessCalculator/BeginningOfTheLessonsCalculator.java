package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.DAYS;
import code.model.enumes.GROUP;
import code.model.enumes.HOUR;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BeginningOfTheLessonsCalculator implements FitnessCalculator{

    private final int fitness;

    @Override
    public int calculateFitness(List<Lesson> lessons) {
        return this.fitness + checkStartTheLessons(lessons);
    }

    private int checkStartTheLessons(List<Lesson> lessons) {
        int fitness = 0;

        for (GROUP group : GROUP.values()) {
            Map<DAYS, HOUR> startLessonsMap = new HashMap<>();
            startLessonsMap.put(DAYS.MONDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.TUESDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.WEDNESDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.THURSDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.FRIDAY, HOUR.H14_35_15_20);

            for (Lesson lesson : lessons) {
                if (lesson.group().equals(group)){
                    DAYS day = lesson.day();
                    HOUR hour = lesson.hour();
                    if (startLessonsMap.get(day).ordinal()>hour.ordinal()) startLessonsMap.replace(day, hour);
                }
            }
            for (DAYS day : startLessonsMap.keySet())
                if (startLessonsMap.get(day).ordinal() > HOUR.H8_55_9_40.ordinal()) fitness++;
        }
        return fitness;
    }
}
