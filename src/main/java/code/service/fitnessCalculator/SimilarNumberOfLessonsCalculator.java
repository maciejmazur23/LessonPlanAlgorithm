package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.DAYS;
import code.model.enumes.GROUP;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SimilarNumberOfLessonsCalculator implements FitnessCalculator {

    private final FitnessCalculator fitnessCalculator;

    @Override
    public int calculateFitness(List<Lesson> lessons) {
        return checkNumberOfLessonsInWeek(lessons) + fitnessCalculator.calculateFitness(lessons);
    }

    private int checkNumberOfLessonsInWeek(List<Lesson> chromosome) {
        int fitness = 0;

        for (GROUP group : GROUP.values()) {
            int lessonsInWeek = 0;
            Map<DAYS, Integer> lessonsInDay = getDaysIntegerMap();

            for (Lesson lesson : chromosome) {
                lessonsInWeek = checkGroup(group, lessonsInWeek, lessonsInDay, lesson);
            }

            BigDecimal averageLessonsInDay = BigDecimal.valueOf(lessonsInWeek)
                    .divide(BigDecimal.valueOf(5), 0, RoundingMode.HALF_UP);

            for (DAYS day : lessonsInDay.keySet()) {
                fitness = getFitness(fitness, lessonsInDay, averageLessonsInDay, day);
            }
        }
        return fitness;
    }

    private int getFitness(int fitness, Map<DAYS, Integer> lessonsInDay, BigDecimal averageLessonsInDay, DAYS day) {
        if ((lessonsInDay.get(day) > averageLessonsInDay.intValue() + 1)
                || (lessonsInDay.get(day) < averageLessonsInDay.intValue() - 1)) {
            fitness += Math.abs(lessonsInDay.get(day) - averageLessonsInDay.intValue());
        }
        return fitness;
    }

    private int checkGroup(GROUP group, int lessonsInWeek, Map<DAYS, Integer> lessonsInDay, Lesson lesson) {
        if (lesson.group().equals(group)) {
            Integer integer = lessonsInDay.get(lesson.day());
            integer++;
            lessonsInDay.replace(lesson.day(), integer);

            lessonsInWeek++;
        }
        return lessonsInWeek;
    }

    private Map<DAYS, Integer> getDaysIntegerMap() {
        Map<DAYS, Integer> lessonsInDay = new HashMap<>();
        lessonsInDay.put(DAYS.MONDAY, 0);
        lessonsInDay.put(DAYS.TUESDAY, 0);
        lessonsInDay.put(DAYS.WEDNESDAY, 0);
        lessonsInDay.put(DAYS.THURSDAY, 0);
        lessonsInDay.put(DAYS.FRIDAY, 0);
        return lessonsInDay;
    }
}
