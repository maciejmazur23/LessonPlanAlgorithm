package code.model.enumes;

public enum DAYS {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

    public static DAYS getByIndex(int index) {
        return DAYS.values()[index];
    }
}
