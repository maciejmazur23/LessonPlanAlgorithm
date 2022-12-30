package code.model.enumes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GROUP {

        A1("1A"), B1("1B"), C1("1C"), D1("1D"), E1("1E"), F1("1F"), G1("1G"),
        A2("2A"), B2("2B"), C2("2C"), D2("2D"), E2("2E"), F2("2F"), G2("2G"),
        A3("3A"), B3("3B"), C3("3C"), D3("3D"), E3("3E"), F3("3F"), G3("3G");

    private final String name;

    private final int maxIndex = 20;
}
