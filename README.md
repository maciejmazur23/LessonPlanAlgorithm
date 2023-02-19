# LessonPlanAlgorithm

## About the project

Lesson Plan Algorithm was created as part of a final project on Combinatorial Optimization at the Poznan University of
Technology. The program optimizes the timetable given at the beginning according to the selected strategy. The program
uses a genetic algorithm.

## Developer guide

As an input parameter, the user provides one of the given forms of optimization:

* WINDOWS
* SIMILAR_NUMBER_OF_LESSONS
* LESSONS_START_AT_THE_BEGINNING_OF_THE_DAY

In the resources directory, there is an **input.json** file that contains the timetable in json format. The algorithm
breaks this schedule by removing days, hours, and classrooms. From the resulting **GroupTeacherSubject** classes, the
program composes a number of **sizeOfPopulation** plans and checks their correctness. If a valid plan is found, the
algorithm ends. If not, it mixes up the plan elements and validates again.