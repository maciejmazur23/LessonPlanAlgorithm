package code.service;

public abstract class TimetableService {

    public abstract void createTimetableFiles(Timetable timetable);

    public abstract StringBuilder getStringPlanes(Timetable timetable);
}
