package code.service.fitnessCalculator;

import code.model.Lesson;

import java.util.List;

public interface FitnessCalculator {

    int calculateFitness(List<Lesson> lessons);

}
