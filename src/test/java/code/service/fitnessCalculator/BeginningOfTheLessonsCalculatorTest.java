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
class BeginningOfTheLessonsCalculatorTest {

    @InjectMocks
    private BeginningOfTheLessonsCalculator beginningOfTheLessonsCalculator;

    @Mock
    private CorrectnessCalculator correctnessCalculator;

    private List<Lesson> lessons;

    @BeforeEach
    void setUp() {
        lessons = new ArrayList<>();
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_55_9_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H9_50_10_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H10_55_11_40, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H11_50_12_35, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H12_45_13_30, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H13_40_14_25, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H14_35_15_20, GROUP.A1, TEACHER.AL, SUBJECT.MATH, ROOM.R_1));
    }

    @Test
    @DisplayName("Start lessons on 8.00")
    void test1() {
        //given
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = beginningOfTheLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Start lessons on 8.55")
    void test2() {
        //given
        lessons.remove(0);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = beginningOfTheLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Start lessons on 9.50")
    void test3() {
        //given
        lessons.remove(0);
        lessons.remove(0);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 1;

        //when
        int result = beginningOfTheLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Start lessons on 10.55 and after")
    void test4() {
        //given
        lessons.remove(0);
        lessons.remove(0);
        lessons.remove(0);
        lessons.remove(0);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 1;

        //when
        int result = beginningOfTheLessonsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }
}