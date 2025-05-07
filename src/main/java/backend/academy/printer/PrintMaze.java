package backend.academy.printer;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.service.OutputStream;
import java.util.List;

public class PrintMaze {
    private final OutputStream outputStream;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WALL = "\033[48;5;236m";
    private static final String ANSI_PASSAGE = "\033[38;5;240m";
    private static final String ANSI_GREEN = "\033[38;5;46m";
    private static final String ANSI_END = "\033[38;5;196m";
    private static final String ANSI_GOOD_TERRAIN = "\033[38;5;154m";
    private static final String ANSI_SWAMP = "\033[38;5;33m";
    private static final String ANSI_PATH_SWAMP = "\033[38;5;75m";

    public PrintMaze(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void printMaze(int height, int width, Cell[][] grid, List<Cell> path) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = grid[y][x];

                boolean cellInPath =
                    path != null && path.contains(cell) && cell.type() != Type.START && cell.type() != Type.END;

                switch (cell.type()) {
                    case WALL -> outputStream.print(ANSI_WALL + "█" + ANSI_RESET);
                    case PASSAGE -> outputStream.print(getColoredString(cell, cellInPath));
                    case START -> outputStream.print(ANSI_GREEN + "A" + ANSI_RESET);
                    case END -> outputStream.print(ANSI_END + "B" + ANSI_RESET);
                    default -> throw new IllegalStateException("Unexpected value: " + cell.type());
                }
            }
            outputStream.println("");
        }
    }

    private String getColoredString(Cell cell, boolean cellInPath) {
        String symbol = cellInPath ? "●" : " ";
        if (cellInPath) {
            return getPathColorBySurface(cell) + "●" + ANSI_RESET;
        }
        switch (cell.surfaceType()) {
            case GOOD_TERRAIN -> {
                return ANSI_GOOD_TERRAIN + "▒" + ANSI_RESET;
            }
            case SWAMP -> {
                return ANSI_SWAMP + "░" + ANSI_RESET;
            }
            default -> {
                return ANSI_PASSAGE + symbol + ANSI_RESET;
            }
        }
    }

    private String getPathColorBySurface(Cell cell) {
        return switch (cell.surfaceType()) {
            case GOOD_TERRAIN -> ANSI_GREEN;
            case SWAMP -> ANSI_PATH_SWAMP;
            default -> ANSI_PASSAGE;
        };
    }
}
