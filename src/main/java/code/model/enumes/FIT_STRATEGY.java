package code.model.enumes;

import lombok.Getter;

@Getter
public enum FIT_STRATEGY {

    WINDOWS("The largest number of windows allowed is 1"),
    SIMILAR_NUMBER_OF_LESSONS("Similar number of lessons in all days"),
    LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY("Lessons start at the beginning of the day")
    ;

    private final String explanation;

    FIT_STRATEGY(String explanation1) {
        this.explanation = explanation1;
    }

}
