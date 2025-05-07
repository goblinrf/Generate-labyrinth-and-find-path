package backend.academy.validation;

import backend.academy.factory.PrinterFactory;
import backend.academy.printer.Printer;
import backend.academy.utils.ReadDataFromTxtFile;

@SuppressWarnings("ConstantName")
public class ValidationOutput {
    private static final Printer printer = PrinterFactory.createPrinter();

    public boolean validationNumber(String numberStr) {
        int number;
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            printer.println("Введите число");
            return false;
        }
        if (number < 0) {
            printer.println("Число должно быть положительным!");
            return false;
        }
        return true;
    }

    public boolean validationAlgorithm(String path, String algorithm) {
        return ReadDataFromTxtFile.getAlgorithmsFromFile(path).contains(algorithm.toLowerCase());
    }
}
