package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.DAYS;
import code.model.enumes.GROUP;
import code.model.enumes.HOUR;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WindowsCalculator implements FitnessCalculator {

    private final FitnessCalculator fitnessCalculator;

    @Override
    public int calculateFitness(List<Lesson> lessons) {
        return checkWindowsBetweenLessons(lessons) + fitnessCalculator.calculateFitness(lessons);
    }

    private int checkWindowsBetweenLessons(List<Lesson> chromosome) {
        int fitness = 0;

        for (GROUP group : GROUP.values()) {
            Map<DAYS, Integer> windowsInDayMap = new HashMap<>();
            Map<HOUR, Integer> hoursMap = new HashMap<>();

            for (DAYS day : DAYS.values()) {
                windowsInDayMap.put(day, 0);

                for (HOUR hour : HOUR.values())
                    hoursMap.put(hour, 0);

                for (Lesson lesson : chromosome)
                    if (lesson.group().equals(group) && lesson.day().equals(day))
                        hoursMap.replace(lesson.hour(), 1);

                List<Integer> list = hoursMap.entrySet().stream()
                        .sorted(Comparator.comparing(e -> e.getKey().ordinal()))
                        .map(Map.Entry::getValue)
                        .toList();

                calculateWindowsInDay(windowsInDayMap, day, list);
            }

            fitness = getFitness(fitness, windowsInDayMap);
        }

        return fitness;
    }

    private void calculateWindowsInDay(Map<DAYS, Integer> windowsInDayMap, DAYS day, List<Integer> list) {
        int windowsInDay = 0, i = 0;

        boolean haveNextLesson = true;

        for (Integer integer : list) {
            if (integer == 1) {
                windowsInDay += i;
                i = 0;
                haveNextLesson = false;
            }
            if (!haveNextLesson && integer == 0) i++;
        }
        windowsInDayMap.replace(day, windowsInDay);
    }

    private int getFitness(int fitness, Map<DAYS, Integer> windowsInDayMap) {
        int addToFitness = 0;

        for (Integer integer : windowsInDayMap.values()) if (integer > 1) addToFitness += integer;

        if (addToFitness > 0) fitness += addToFitness;

        return fitness;
    }
}
