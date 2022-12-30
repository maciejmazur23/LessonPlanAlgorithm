package code.model.enumes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ROOM {
    R_1, R_2, R_3, R_4, R_5, R_6, R_7, R_8, R_9, R_10, R_11, R_12, R_13,
    R_14, R_15, R_16, R_17, R_18, R_19, R_20, R_21, R_22, R_23, R_24, R_25,
    R_26, R_WF1, R_WF2, R_WF3;

    public static ROOM getRoom(int index) {
        return ROOM.values()[index];
    }

}
