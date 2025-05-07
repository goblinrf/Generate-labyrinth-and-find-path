package backend.academy.factory;

import backend.academy.service.OutputStream;
import backend.academy.service.impl.Output.ConsoleOutput;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("LSC_LITERAL_STRING_COMPARISON")
public class OutputFactory {
    public static OutputStream createOutput(String outputType) {
        if (outputType.equalsIgnoreCase("console")) {
            return new ConsoleOutput();
        } else {
            throw new IllegalArgumentException("Unknown output type: " + outputType);
        }
    }

    private OutputFactory() {
    }
}
