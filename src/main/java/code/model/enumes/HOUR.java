package code.model.enumes;

import lombok.Getter;

@Getter
public enum HOUR {
    H8_00_8_45("8.00-8.45"),
    H8_55_9_40("8.55-9.40"),
    H9_50_10_35("9.50-10.35"),
    H10_55_11_40("10.55-11.40"),
    H11_50_12_35("11.50-12.35"),
    H12_45_13_30("12.45-13.30"),
    H13_40_14_25("13.40-14.25"),
    H14_35_15_20("14.35-15.20");

    private final String time;

    HOUR(String time) {
        this.time = time;
    }

    public static int getMaxIndex(){
        return HOUR.values().length;
    }

    public static HOUR getHour(int index){
        return HOUR.values()[index];
    }
}
