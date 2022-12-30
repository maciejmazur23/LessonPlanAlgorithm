package code.model.enumes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TEACHER {
    EK("chemistry, physics"), JW("geography"), MM("biology, geography"), WJ("physics"),
    EL("math"), BD("informatics"), KT("english"), LZ("german"), RC("polish"),
    BW("history"), JM("wos, history"), PS("wf"), AJ("history"), RS("biology"),
    AL("math"), JR("english"), MD("polish, history"), WK("informatics"), MN("math"),
    PA("biology"), TJ("wf"), AF("chemistry"), MZ("english"), IC("german"),
    HM("math"), SP("polish"), SA("history"), KW("polish"), AK("english"),
    PD("informatics"), AS("english"), JK("polish"), MH("math"), KK("english"),
    HK("polish"), AP("english"), MO("wf"), LB("math"), JG("math");

    private final String subject;
}
