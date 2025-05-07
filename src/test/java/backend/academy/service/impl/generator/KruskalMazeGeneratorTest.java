package backend.academy.service.impl.generator;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.model.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KruskalMazeGeneratorTest {
    private KruskalMazeGenerator mazeGenerator;
    private Cell[][] grid;
    private int width = 5;
    private int height = 5;

    @BeforeEach
    public void setUp() {
        mazeGenerator = new KruskalMazeGenerator();
        grid = new Cell[height][width];

        // Заполняем сетку стенами
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, Type.WALL);
            }
        }
    }

    @Test
    @DisplayName("Given a grid, when maze is generated, then passages should be created between cells")
    public void givenGrid_whenGenerateMaze_thenPassagesCreated() {
        // Given

        // When
        mazeGenerator.generateMaze(grid, width, height);

        // Then
        boolean hasPassages = false;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].type() == Type.PASSAGE) {
                    hasPassages = true;
                    break;
                }
            }
        }

        assertTrue(hasPassages, "Должны быть созданы проходы в лабиринте.");
    }

    @Test
    @DisplayName("Given two adjacent cells, when a wall is removed, then a passage is created between them")
    public void givenAdjacentCells_whenRemoveWall_thenPassageCreated() {
        // Given
        Edge edge = new Edge(1, 1, 1, 2); // Клетки (1,1) и (1,2)
        mazeGenerator.generateMaze(grid, width, height);

        // Whe
        mazeGenerator.removeWall(grid, edge);

        // Then
        assertEquals(Type.PASSAGE, grid[1][1].type(), "Должна быть создана клетка-проход в (1,1)");
        assertEquals(Type.PASSAGE, grid[1][2].type(), "Должна быть создана клетка-проход в (1,2)");
    }

    @Test
    @DisplayName("Given grid, when maze is generated, then no walls should remain between connected cells")
    public void givenGrid_whenGenerateMaze_thenNoWallsBetweenConnectedCells() {

        // When
        mazeGenerator.generateMaze(grid, width, height);

        // Then
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].type() == Type.PASSAGE) {
                    if (x + 1 < width) {
                        assertTrue(
                            grid[y][x + 1].type() == Type.PASSAGE || grid[y][x + 1].type() == Type.WALL,
                            "Должны быть проходы между клетками (" + y + "," + x + ") и (" + y + "," + (x + 1) + ").");
                    }
                    if (y + 1 < height) {
                        assertTrue(
                            grid[y + 1][x].type() == Type.PASSAGE || grid[y + 1][x].type() == Type.WALL,
                            "Должны быть проходы между клетками (" + y + "," + x + ") и (" + (y + 1) + "," + x + ").");
                    }
                }
            }
        }
    }
}
