package backend.academy.reader;

import backend.academy.factory.PrinterFactory;
import backend.academy.printer.Printer;
import backend.academy.service.InputStream;
import backend.academy.validation.ValidationOutput;

@SuppressWarnings("ConstantName")
public class ReaderMazeParams {
    private static final Printer printer = PrinterFactory.createPrinter();
    private final InputStream inputStream;
    ValidationOutput validationOutput = new ValidationOutput();

    public ReaderMazeParams(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int readNumber(String str) {
        String size;
        do {
            printer.print("Введите " + str);
            size = inputStream.read(": ");
        }
        while (!validationOutput.validationNumber(size));
        return Integer.parseInt(size);
    }

    public String readAlgorithm(String path) {
        String algorithm;
        do {
            algorithm = inputStream.read("Введите алгоритм для генерации лабиринта "
                + "из выше перечисленных: ");
        }
        while (!validationOutput.validationAlgorithm(path, algorithm));
        return algorithm;
    }

}
