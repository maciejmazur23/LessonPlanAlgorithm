package code.service.fitnessCalculator;

import code.model.enumes.FIT_STRATEGY;

public abstract class FitnessCalculatorFactory {

    public static FitnessCalculator getFitnessCalculator(FitnessCalculator correctnessCalculator, FIT_STRATEGY strategy) {
        return switch (strategy) {
            case WINDOWS -> new WindowsCalculator(correctnessCalculator);
            case LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY -> new BeginningOfTheLessonsCalculator(correctnessCalculator);
            case SIMILAR_NUMBER_OF_LESSONS -> new SimilarNumberOfLessonsCalculator(correctnessCalculator);
        };
    }
}