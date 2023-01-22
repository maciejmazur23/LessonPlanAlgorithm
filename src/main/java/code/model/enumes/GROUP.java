package code.model.enumes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GROUP {

        A1("1A"), B1("1B"), C1("1C"), D1("1D"),
        A2("2A"), B2("2B"), C2("2C"), D2("2D"), E2("2E"),
        A3("3A"), B3("3B"), C3("3C"), D3("3D");

    private final String name;

    private final int maxIndex = 20;
}
