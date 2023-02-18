package code.service;

import code.model.Lesson;
import code.model.enumes.DAYS;
import code.model.enumes.GROUP;
import code.model.enumes.HOUR;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.List;

public class TimetableServiceImpl extends TimetableService {

    @Override
    public void createTimetableFiles(Timetable timetable) {
        StringBuilder stringBuilder = getStringPlanes(timetable);
        generateFile(stringBuilder);
    }

    @Override
    public StringBuilder getStringPlanes(Timetable timetable) {
        List<Lesson> lessons = timetable.getChromosome();
        return generateFullPlan(lessons);
    }


    private StringBuilder generateFullPlan(List<Lesson> lessons) {
        StringBuilder stringBuilder = new StringBuilder();
        String format = "| %12s | %19s | %19s | %19s | %19s | %19s |\n";
        for (GROUP group : GROUP.values()) {
            stringBuilder.append(group).append("\n");
            stringBuilder.append(String.format(format, "", DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

            for (HOUR hour : HOUR.values()) {
                stringBuilder.append(String.format("| %12s |", hour.getTime()));

                for (DAYS day : DAYS.values()) {
                    String str = "";
                    for (Lesson lesson : lessons)
                        if (lesson.group().equals(group) && lesson.hour().equals(hour) && lesson.day().equals(day)) {
                            str = lesson.subject().name() + " " + lesson.room().name() + " " + lesson.teacher().name();
                        }

                    stringBuilder.append(String.format(" %19s |", str));
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private void generateFile(StringBuilder builder) {
        String path = "timetables";
        try {
            if (Files.notExists(Paths.get(path))) Files.createDirectory(Paths.get(path));

            Files.deleteIfExists(Paths.get(path + "/" + "allPlanes"));

            Path file = Files.createFile(Paths.get(path + "/" + "allPlanes"));

            BufferedWriter writer = Files.newBufferedWriter(file);
            writer.write(builder.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
