package code.service;

import code.model.GroupTeacherSubject;
import code.model.InputData;
import code.service.fitnessCalculator.FitnessCalculator;

import java.util.List;

public interface PopulationGenerator {
    List<Timetable> generatePopulation(int sizeOfPopulation, List<GroupTeacherSubject> data, FitnessCalculator fitnessCalculator);

    List<Timetable> getNewGeneration(InputData inputData, List<Timetable> population, int sizeFromOldToNewGeneration);
}
