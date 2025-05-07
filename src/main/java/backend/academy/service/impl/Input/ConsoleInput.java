package backend.academy.service.impl.Input;

import backend.academy.service.InputStream;
import backend.academy.utils.ScannerUtils;
import java.util.function.Function;

public class ConsoleInput implements InputStream {
    @Override
    public String read(String message) {
       return ScannerUtils.getInput(message, Function.identity());
    }
}
