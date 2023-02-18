package code.service;

import code.model.GroupTeacherSubject;
import code.model.InputData;
import code.model.Lesson;
import code.model.enumes.*;
import code.service.fitnessCalculator.FitnessCalculator;

import java.util.List;
import java.util.Random;

public interface PopulationGenerator {
    List<Timetable> generatePopulation(int sizeOfPopulation, List<GroupTeacherSubject> data, FitnessCalculator fitnessCalculator);

    List<Timetable> getNewGeneration(InputData inputData, List<Timetable> population, int sizeFromOldToNewGeneration);

    static Lesson getLesson(GroupTeacherSubject groupTeacherSubject){
        Random random = new Random();
        GROUP group = groupTeacherSubject.group();
        SUBJECT subject = groupTeacherSubject.subject();
        TEACHER teacher = groupTeacherSubject.teacher();

        DAYS day = DAYS.getByIndex(random.nextInt(0, 5));

        HOUR hour = HOUR.getHour(random.nextInt(HOUR.getMaxIndex()));

        int roomInt;
        if (subject.equals(SUBJECT.WF)) {
            roomInt = random.nextInt(26, 29);
        } else roomInt = random.nextInt(0, 26);

        ROOM room = ROOM.getRoom(roomInt);

        return new Lesson(day, hour, group, teacher, subject, room);
    }
}
