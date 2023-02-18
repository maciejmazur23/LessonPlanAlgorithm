package code.service;

import code.model.GroupTeacherSubject;
import code.model.Lesson;
import code.service.fitnessCalculator.FitnessCalculator;
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
                Lesson lesson = PopulationGenerator.getLesson(data.get(i));
                child_chromosome.add(lesson);
            }
        }
        return new Timetable(child_chromosome, this.fitnessCalculator, data);
    }

}