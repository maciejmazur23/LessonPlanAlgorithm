package code;

import code.model.GroupTeacherSubject;
import code.model.InputData;
import code.model.Lesson;
import code.service.*;

import java.util.Comparator;
import java.util.List;

public class AlgorithmRunner {

    public static void main(String[] args) {
        AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        algorithmRunner.runAlgorithm();
    }

    private void runAlgorithm() {
        TimetableService timetableService = new TimetableServiceImpl();
        JsonService jsonService = new JsonServiceImpl();
        PopulationGenerator populationGenerator = new PopulationGeneratorImpl();

        List<Lesson> listFromJson = getListFromJson(jsonService);
        InputData inputData = new InputData(listFromJson);

        inputData.insert();

        List<GroupTeacherSubject> data = inputData.getDataToTimetable();
        FitnessCalculator fitnessCalculator = new FitnessCalculatorImpl(inputData.getFit_strategy());

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
