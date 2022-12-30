package code.service;

import code.model.Lesson;
import code.model.enumes.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class FitnessCalculatorImpl implements FitnessCalculator {

    private final FIT_STRATEGY fit_strategy;

    @Override
    public int calculateFitness(List<Lesson> chromosome) {
        int checkLessons = checkLessons(chromosome);
        int checkOther = 0;

        switch (fit_strategy) {
            case WINDOWS -> checkOther = checkWindowsBetweenLessons(chromosome);
            case LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY -> checkOther = checkStartTheLessons(chromosome);
            case SIMILAR_NUMBER_OF_LESSONS -> checkOther = checkNumberOfLessonsInWeek(chromosome);
        }
        return checkLessons + checkOther;
    }

    private int checkLessons(List<Lesson> chromosome) {
        int fitness = 0;
        Map<RoomTime, Integer> roomTimeMap = new HashMap<>();
        Map<GroupTime, Integer> groupTimeMap = new HashMap<>();
        Map<TeacherTime, Integer> teacherTimeMap = new HashMap<>();

        completeAllMaps(chromosome, teacherTimeMap, groupTimeMap, roomTimeMap);

        for (Integer integer : roomTimeMap.values()) if (integer > 1) fitness += (integer - 1);
        for (Integer integer : groupTimeMap.values()) if (integer > 1) fitness += (integer - 1);
        for (Integer integer : teacherTimeMap.values()) if (integer > 1) fitness += (integer - 1);

        return fitness;
    }

    private void completeAllMaps(List<Lesson> chromosome, Map<TeacherTime, Integer> teacherTimeMap,
                                 Map<GroupTime, Integer> groupTimeMap, Map<RoomTime, Integer> roomTimeMap
    ) {
        for (Lesson lesson : chromosome) {
            DAYS day = lesson.getDay();
            HOUR hour = lesson.getHour();

            updateTeacherMap(teacherTimeMap, lesson, day, hour);
            updateGroupMap(groupTimeMap, lesson, day, hour);
            updateRoomMap(roomTimeMap, lesson, day, hour);
        }
    }

    private void updateRoomMap(Map<RoomTime, Integer> roomTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        RoomTime roomTime = new RoomTime(day, hour, lesson.getRoom());
        if (roomTimeMap.containsKey(roomTime)) {
            Integer integer = roomTimeMap.get(roomTime);
            integer++;
            roomTimeMap.replace(roomTime, integer);
        } else roomTimeMap.put(roomTime, 1);
    }

    private void updateGroupMap(Map<GroupTime, Integer> groupTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        GroupTime groupTime = new GroupTime(day, hour, lesson.getGroup());
        if (groupTimeMap.containsKey(groupTime)) {
            Integer integer = groupTimeMap.get(groupTime);
            integer++;
            groupTimeMap.replace(groupTime, integer);
        } else groupTimeMap.put(groupTime, 1);
    }

    private void updateTeacherMap(Map<TeacherTime, Integer> teacherTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        TeacherTime teacherTime = new TeacherTime(day, hour, lesson.getTeacher());
        if (teacherTimeMap.containsKey(teacherTime)) {
            Integer integer = teacherTimeMap.get(teacherTime);
            integer++;
            teacherTimeMap.replace(teacherTime, integer);
        } else teacherTimeMap.put(teacherTime, 1);
    }



    private int checkStartTheLessons(List<Lesson> chromosome) {
        int fitness = 0;

        for (GROUP group : GROUP.values()) {
            Map<DAYS, HOUR> startLessonsMap = new HashMap<>();
            startLessonsMap.put(DAYS.MONDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.TUESDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.WEDNESDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.THURSDAY, HOUR.H14_35_15_20);
            startLessonsMap.put(DAYS.FRIDAY, HOUR.H14_35_15_20);

            for (Lesson lesson : chromosome) {
                if (lesson.getGroup().equals(group)){
                    DAYS day = lesson.getDay();
                    HOUR hour = lesson.getHour();
                    if (startLessonsMap.get(day).ordinal()>hour.ordinal()) startLessonsMap.replace(day, hour);
                }
            }
            for (DAYS day : startLessonsMap.keySet())
                if (startLessonsMap.get(day).ordinal() > HOUR.H8_55_9_40.ordinal()) fitness++;
        }
        return fitness;
    }

    private int checkNumberOfLessonsInWeek(List<Lesson> chromosome) {
        int fitness = 0;

        for (GROUP group : GROUP.values()) {
            int lessonsInWeek = 0;
            Map<DAYS, Integer> lessonsInDay = new HashMap<>();
            lessonsInDay.put(DAYS.MONDAY, 0);
            lessonsInDay.put(DAYS.TUESDAY, 0);
            lessonsInDay.put(DAYS.WEDNESDAY, 0);
            lessonsInDay.put(DAYS.THURSDAY, 0);
            lessonsInDay.put(DAYS.FRIDAY, 0);

            for (Lesson lesson : chromosome) {
                if (lesson.getGroup().equals(group)) {
                    Integer integer = lessonsInDay.get(lesson.getDay());
                    integer++;
                    lessonsInDay.replace(lesson.getDay(), integer);

                    lessonsInWeek++;
                }
            }

            BigDecimal averageLessonsInDay = BigDecimal.valueOf(lessonsInWeek).divide(BigDecimal.valueOf(5), 0, RoundingMode.HALF_UP);
            for (DAYS day : lessonsInDay.keySet()) {
                if ((lessonsInDay.get(day) > averageLessonsInDay.intValue() + 1) || (lessonsInDay.get(day) < averageLessonsInDay.intValue() - 1))
                    fitness += Math.abs(lessonsInDay.get(day) - averageLessonsInDay.intValue());
            }
        }

        return fitness;
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
                    if (lesson.getGroup().equals(group) && lesson.getDay().equals(day))
                        hoursMap.replace(lesson.getHour(), 1);

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

    private record RoomTime(DAYS day, HOUR hour, ROOM room) {
    }


    private record GroupTime(DAYS day, HOUR hour, GROUP group) {
    }

    private record TeacherTime(DAYS day, HOUR hour, TEACHER teacher) {
    }

}