package code;

import code.model.GroupTeacherSubject;
import code.model.InputData;
import code.model.Lesson;
import code.model.enumes.FIT_STRATEGY;
import code.service.*;
import code.service.fitnessCalculator.CorrectnessCalculator;
import code.service.fitnessCalculator.FitnessCalculator;
import code.service.fitnessCalculator.FitnessCalculatorFactory;

import java.util.Comparator;
import java.util.List;

public class AlgorithmRunner {

    public static void main(String[] args) {
        AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        algorithmRunner.runAlgorithm(args);
    }

    private void runAlgorithm(String[] args) {
        TimetableService timetableService = new TimetableServiceImpl();
        PopulationGenerator populationGenerator = new PopulationGeneratorImpl();
        JsonService jsonService = new JsonServiceImpl();
        FitnessCalculator correctnessCalculator = new CorrectnessCalculator();

        List<Lesson> listFromJson = getListFromJson(jsonService);
        InputData inputData = new InputData(listFromJson);

        inputData.setStrategy(FIT_STRATEGY.valueOf(args[0]));

        List<GroupTeacherSubject> data = inputData.getDataToTimetable();

        FitnessCalculator fitnessCalculator = FitnessCalculatorFactory.getFitnessCalculator(
                correctnessCalculator, inputData.getStrategy(), listFromJson
        );

        int PERCENT = inputData.getPercent();
        int sizeOfPopulation = inputData.getSizeOfPopulation();
        int generation = 1;
        List<Timetable> population = populationGenerator.generatePopulation(sizeOfPopulation, data, fitnessCalculator);

        while (true) {
            population = population.stream()
                    .sorted(Comparator.comparing(Timetable::getFitness))
                    .toList();

            if (population.get(0).getFitness() == 0) break;

            int sizeFromOldToNewGeneration = (PERCENT * sizeOfPopulation) / 100;
            population = populationGenerator.getNewGeneration(inputData, population, sizeFromOldToNewGeneration);

            printGeneration(generation, population);
            generation++;
        }
        printGeneration(generation, population);

        Timetable timetable = population.get(0);
        saveResult(timetableService, jsonService, timetable);
    }

    private void saveResult(TimetableService timetableService, JsonService jsonService, Timetable timetable) {
        timetableService.createTimetableFiles(timetable);
        String json = jsonService.getJsonFromList(timetable.getChromosome());
        jsonService.saveJsonFile(json);
    }

    private List<Lesson> getListFromJson(JsonService jsonService) {
        return jsonService.getListFromJson(jsonService.getJsonText());
    }

    private void printGeneration(int generation, List<Timetable> population) {
        System.out.println("Generation: " + generation + " Fitness: " + population.get(0).getFitness());
    }
}
