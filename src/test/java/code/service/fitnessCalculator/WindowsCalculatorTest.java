package code.service.fitnessCalculator;

import code.model.Lesson;
import code.model.enumes.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WindowsCalculatorTest {

    @InjectMocks
    private WindowsCalculator windowsCalculator;

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
    }

    @Test
    @DisplayName("Check if the list does not contain windows")
    void test1() {
        //given
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = windowsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if the list contains one window")
    void test2() {
        //given
        lessons.remove(3);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 0;

        //when
        int result = windowsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if list contains windows on top of each other")
    void test3() {
        //given
        lessons.remove(2);
        lessons.remove(3);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 2;

        //when
        int result = windowsCalculator.calculateFitness(lessons);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if list contains windows out of order")
    void test4() {
        //given
        lessons.remove(1);
        lessons.remove(4);
        BDDMockito.given(correctnessCalculator.calculateFitness(lessons)).willReturn(0);
        int expected = 2;

        //when
        int result = windowsCalculator.calculateFitness(lessons);

        //then
        assertEquals(expected, result);
    }

}