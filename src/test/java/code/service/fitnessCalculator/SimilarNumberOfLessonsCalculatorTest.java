package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimilarNumberOfLessonsCalculatorTest {

    @InjectMocks
    private SimilarNumberOfLessonsCalculator similarNumberOfLessonsCalculator;

    @Mock
    private CorrectnessCalculator correctnessCalculator;

    private List<Lesson> lessons;

    @BeforeEach
    void beforeEach() {
        lessons = new ArrayList<>();
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));

        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.WEDNESDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));

        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.TUESDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));

        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.THURSDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));

        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.FRIDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
    }

    @Test
    @DisplayName("All day 8 lessons")
    void test1() {
        //given
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = similarNumberOfLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("All days maximum deviation 1 hour")
    void test2() {
        //given
        lessons.remove(7);
        lessons.remove(20);
        lessons.remove(30);
        lessons.remove(30);

        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = similarNumberOfLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Different number in days")
    void test3() {
        //given
        lessons.remove(33);
        lessons.remove(33);
        lessons.remove(33);
        lessons.remove(33);

        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 3;

        //when
        int result = similarNumberOfLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

}