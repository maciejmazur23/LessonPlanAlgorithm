package code.service;

import code.model.GroupTeacherSubject;
import code.model.Lesson;
import code.model.enumes.DAYS;
import code.model.enumes.HOUR;
import code.model.enumes.ROOM;
import code.model.enumes.SUBJECT;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@ToString
public class Timetable {
    private final Random random = new Random();
    private final FitnessCalculator fitnessCalculator;
    private final List<GroupTeacherSubject> data;
    private final List<Lesson> chromosome;
    private final int fitness;

    public Timetable(List<Lesson> chromosome, FitnessCalculator fitnessCalculator, List<GroupTeacherSubject> data){
        this.chromosome = chromosome;
        this.fitnessCalculator = fitnessCalculator;
        this.data = data;
        this.fitness = fitnessCalculator.calculateFitness(chromosome);
    }

    public Timetable getNewChild(Timetable parent2) {
        List<Lesson> child_chromosome = new ArrayList<>();

        for (int i = 0; i < chromosome.size(); i++) {
            double rand = random.nextDouble(0, 1);

            if (rand < 0.4994){
                child_chromosome.add(chromosome.get(i));
            } else if (rand < 0.9988){
                child_chromosome.add(parent2.getChromosome().get(i));
            } else {
                Lesson lesson = getLesson(i);
                child_chromosome.add(lesson);
            }
        }
        return new Timetable(child_chromosome, this.fitnessCalculator, data);
    }

    private Lesson getLesson(int i) {
        GroupTeacherSubject groupTeacherSubject = data.get(i);

        int dayInt = random.nextInt(DAYS.values().length);
        DAYS day = DAYS.getByIndex(dayInt);

        int hourInt = random.nextInt(HOUR.getMaxIndex());
        HOUR hour = HOUR.getHour(hourInt);

        int roomInt;
        if(groupTeacherSubject.getSubject().equals(SUBJECT.WF)){
            roomInt = random.nextInt(26, 29);
        }else {
            roomInt = random.nextInt(0, 26);
        }
        ROOM room = ROOM.getRoom(roomInt);

        return new Lesson(day, hour, groupTeacherSubject.getGroup(),
                groupTeacherSubject.getTeacher(), groupTeacherSubject.getSubject(), room);
    }

}
