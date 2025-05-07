package backend.academy.factory;

import backend.academy.service.InputStream;
import backend.academy.service.impl.Input.ConsoleInput;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("LSC_LITERAL_STRING_COMPARISON")
public class InputFactory {

    public static InputStream createInput(String inputType) {
        if (inputType.equalsIgnoreCase("console")) {
            return new ConsoleInput();
        } else {
            throw new IllegalArgumentException("Unknown input type: " + inputType);
        }
    }

    private InputFactory() {
    }

}
