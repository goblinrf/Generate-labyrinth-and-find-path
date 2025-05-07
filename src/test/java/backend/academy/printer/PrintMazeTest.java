package backend.academy.printer;

import backend.academy.enums.Type;
import backend.academy.factory.OutputFactory;
import backend.academy.model.Cell;
import backend.academy.service.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintMazeTest {

    private Cell[][] grid;
    private int width = 5;
    private int height = 5;
    private PrintMaze printMaze;

    @BeforeEach
    public void setUpGridAndPrinter() {
        grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, Type.WALL);
            }
        }
        grid[0][1] = new Cell(0, 1, Type.START);
        grid[2][3] = new Cell(2, 3, Type.END);

        grid[1][1] = new Cell(1, 1, Type.PASSAGE);
        grid[1][2] = new Cell(1, 2, Type.PASSAGE);
        grid[2][2] = new Cell(2, 2, Type.PASSAGE);
        OutputStream output = OutputFactory.createOutput("console");
        printMaze = new PrintMaze(output);
    }

    @Test
    @DisplayName("Лабиринт должен корректно отображаться в консоли")
    public void givenExpectedMaze_whenPrintGridMaze_thenExpectedMazeEqualsPrinted() {
        // Given
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String expectedOutput = """
            [48;5;236m█[0m[38;5;46mA[0m[48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m
            [48;5;236m█[0m[38;5;240m [0m[38;5;240m [0m[48;5;236m█[0m[48;5;236m█[0m
            [48;5;236m█[0m[48;5;236m█[0m[38;5;240m [0m[38;5;196mB[0m[48;5;236m█[0m
            [48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m
            [48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m[48;5;236m█[0m
            """;

        // When
        printMaze.printMaze(height, width, grid, null);
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");

        // Then
        assertEquals(expectedOutput, actualOutput);
    }
}
