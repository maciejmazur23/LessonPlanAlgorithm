package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorrectnessCalculator implements FitnessCalculator{

    @Override
    public int calculateFitness(List<Lesson> lessons) {
        return checkLessons(lessons);
    }

    private static int checkLessons(List<Lesson> lessons) {
        int fitness = 0;
        Map<RoomTime, Integer> roomTimeMap = new HashMap<>();
        Map<GroupTime, Integer> groupTimeMap = new HashMap<>();
        Map<TeacherTime, Integer> teacherTimeMap = new HashMap<>();

        completeAllMaps(lessons, teacherTimeMap, groupTimeMap, roomTimeMap);

        for (Integer integer : roomTimeMap.values()) if (integer > 1) fitness += (integer - 1);
        for (Integer integer : groupTimeMap.values()) if (integer > 1) fitness += (integer - 1);
        for (Integer integer : teacherTimeMap.values()) if (integer > 1) fitness += (integer - 1);

        return fitness;
    }

    private static void completeAllMaps(List<Lesson> lessons, Map<TeacherTime, Integer> teacherTimeMap,
                                        Map<GroupTime, Integer> groupTimeMap, Map<RoomTime, Integer> roomTimeMap
    ) {
        for (Lesson lesson : lessons) {
            DAYS day = lesson.day();
            HOUR hour = lesson.hour();

            updateTeacherMap(teacherTimeMap, lesson, day, hour);
            updateGroupMap(groupTimeMap, lesson, day, hour);
            updateRoomMap(roomTimeMap, lesson, day, hour);
        }
    }

    private static void updateRoomMap(Map<RoomTime, Integer> roomTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        RoomTime roomTime = new RoomTime(day, hour, lesson.room());
        if (roomTimeMap.containsKey(roomTime)) {
            Integer integer = roomTimeMap.get(roomTime);
            integer++;
            roomTimeMap.replace(roomTime, integer);
        } else roomTimeMap.put(roomTime, 1);
    }

    private static void updateGroupMap(Map<GroupTime, Integer> groupTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        GroupTime groupTime = new GroupTime(day, hour, lesson.group());
        if (groupTimeMap.containsKey(groupTime)) {
            Integer integer = groupTimeMap.get(groupTime);
            integer++;
            groupTimeMap.replace(groupTime, integer);
        } else groupTimeMap.put(groupTime, 1);
    }

    private static void updateTeacherMap(Map<TeacherTime, Integer> teacherTimeMap, Lesson lesson, DAYS day, HOUR hour) {
        TeacherTime teacherTime = new TeacherTime(day, hour, lesson.teacher());
        if (teacherTimeMap.containsKey(teacherTime)) {
            Integer integer = teacherTimeMap.get(teacherTime);
            integer++;
            teacherTimeMap.replace(teacherTime, integer);
        } else teacherTimeMap.put(teacherTime, 1);
    }

    private record RoomTime(DAYS day, HOUR hour, ROOM room) {
    }

    private record GroupTime(DAYS day, HOUR hour, GROUP group) {
    }

    private record TeacherTime(DAYS day, HOUR hour, TEACHER teacher) {
    }

}
