package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class CorrectnessCalculatorTest {

    private static CorrectnessCalculator correctnessCalculator;

    private List<Lesson> lessons;

    @BeforeAll
    static void getInstance(){
        correctnessCalculator = new CorrectnessCalculator();
    }

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
    @DisplayName("All is right")
    void test1() {
        //given
        int expected = 0;

        //when
        int result = correctnessCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Many lessons of that same class in one time")
    void test2(){
        //given
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.SP, SUBJECT.POLISH, ROOM.R_2));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.JW, SUBJECT.GEOGRAPHY, ROOM.R_3));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A1, TEACHER.TJ, SUBJECT.WF, ROOM.R_4));
        int expected = 3;

        //when
        int result = correctnessCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Many lessons in that same room in one time")
    void test3(){
        //given
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.A2, TEACHER.SP, SUBJECT.POLISH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.B3, TEACHER.AS, SUBJECT.ENGLISH, ROOM.R_1));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.C1, TEACHER.JM, SUBJECT.HIS, ROOM.R_1));
        int expected = 3;

        //when
        int result = correctnessCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName("Many lessons in that same teacher in one time")
    void test4(){
        //given
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.C1, TEACHER.AL, SUBJECT.MATH, ROOM.R_2));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.D2, TEACHER.AL, SUBJECT.MATH, ROOM.R_3));
        lessons.add(new Lesson(DAYS.MONDAY, HOUR.H8_00_8_45, GROUP.E3, TEACHER.AL, SUBJECT.MATH, ROOM.R_4));
        int expected = 3;

        //when
        int result = correctnessCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }
}