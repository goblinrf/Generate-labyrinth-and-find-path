package backend.academy.utils;

import backend.academy.factory.PrinterFactory;
import backend.academy.printer.Printer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantName")
public class ReadDataFromTxtFile {
    private static final Printer printer = PrinterFactory.createPrinter();

    public static List<String> getAlgorithmsFromFile(String fileName) {
        List<String> arr = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arr.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            printer.println("Ошибка чтения файла: " + e.getMessage());
        }
        return arr;
    }

    private ReadDataFromTxtFile() {
    }
}
