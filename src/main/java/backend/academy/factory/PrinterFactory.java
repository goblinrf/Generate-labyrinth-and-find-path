package backend.academy.factory;

import backend.academy.printer.Printer;

@SuppressWarnings("HideUtilityClassConstructor")
public class PrinterFactory {
    public static Printer createPrinter() {
        return new Printer();
    }
}
