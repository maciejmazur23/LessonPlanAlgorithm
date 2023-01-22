package code.service;

import code.model.GroupTeacherSubject;
import code.model.InputData;
import code.model.Lesson;
import code.model.enumes.*;
import code.service.fitnessCalculator.FitnessCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationGeneratorImpl implements PopulationGenerator{
    private final Random random = new Random();

    @Override
    public List<Timetable> getNewGeneration(InputData inputData, List<Timetable> population, int sizeFromOldToNewGeneration) {
        int POPULATION_SIZE = inputData.getSizeOfPopulation();
        int PERCENT = inputData.getPercent();
        List<Timetable> new_generation = new ArrayList<>(population.subList(0, sizeFromOldToNewGeneration));

        sizeFromOldToNewGeneration = ((100 - PERCENT) * POPULATION_SIZE) / 100;

        for (int i = 0; i < sizeFromOldToNewGeneration; i++) {
            List<Timetable> subList = population.subList(0, POPULATION_SIZE / 2);

            Timetable child = getChild(POPULATION_SIZE, subList);
            new_generation.add(child);

        }return new_generation;
    }

    private Timetable getChild(int POPULATION_SIZE, List<Timetable> subList) {
        int rand = random.nextInt(POPULATION_SIZE / 2);
        Timetable parent1 = subList.get(rand);

        rand = random.nextInt(POPULATION_SIZE / 2);
        Timetable parent2 = subList.get(rand);

        return parent1.getNewChild(parent2);
    }

    @Override
    public List<Timetable> generatePopulation(
            int POPULATION_SIZE, List<GroupTeacherSubject> listOfData, FitnessCalculator fitnessCalculator
    ) {
        List<Timetable> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Lesson> chromosome = randomTimetable(listOfData);
            Timetable timetable = new Timetable(chromosome, fitnessCalculator, listOfData);
            population.add(timetable);
        }
        return population;
    }

    private List<Lesson> randomTimetable(List<GroupTeacherSubject> listOfData) {
        List<Lesson> lessons = new ArrayList<>();

        for (GroupTeacherSubject groupTeacherSubject : listOfData) {
            GROUP group = groupTeacherSubject.group();
            TEACHER teacher = groupTeacherSubject.teacher();
            SUBJECT subject = groupTeacherSubject.subject();

            DAYS day = DAYS.getByIndex(random.nextInt(0, 5));

            HOUR hour = HOUR.getHour(random.nextInt(HOUR.getMaxIndex()));

            ROOM room = getRoom(groupTeacherSubject);

            Lesson lesson = new Lesson(day, hour, group, teacher, subject, room);
            lessons.add(lesson);
        }
        return lessons;
    }

    private ROOM getRoom(GroupTeacherSubject groupTeacherSubject) {
        int roomInt;
        if (groupTeacherSubject.subject().equals(SUBJECT.WF)) {
            roomInt = random.nextInt(26, 29);
        } else roomInt = random.nextInt(0, 26);

        return ROOM.getRoom(roomInt);
    }

}
