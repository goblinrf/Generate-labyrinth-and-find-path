package backend.academy.service.impl.findpath;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.service.ShortestPathFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BFSTest {
    private Cell[][] grid;
    private final int width = 5;
    private final int height = 5;
    private final BFS bfs = new BFS();
    private Cell start;
    private Cell end;

    @BeforeEach
    public void setupMaze() {
        grid = new Cell[height][width];
        grid[0][0] = new Cell(0, 0, Type.START);
        grid[2][3] = new Cell(2, 3, Type.END);

        grid[0][3] = new Cell(0, 3, Type.WALL);
        grid[1][1] = new Cell(1, 1, Type.WALL);
        grid[1][3] = new Cell(1, 3, Type.WALL);
        grid[2][1] = new Cell(2, 1, Type.WALL);
        grid[3][1] = new Cell(3, 1, Type.WALL);
        grid[3][3] = new Cell(3, 3, Type.WALL);
        grid[4][1] = new Cell(4, 1, Type.WALL);

        start = grid[0][0];
        end = grid[2][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == null) {
                    grid[y][x] = new Cell(y, x, Type.PASSAGE);
                }
            }
        }
    }

    @Test
    @DisplayName("BFS должен найти правильный путь в лабиринте")
    public void givenCorrectPath_whenGetBfsPath_thenBfsPathCorrect() {
        // given
        List<Cell> expectedPath = List.of(
            grid[0][0], grid[0][1], grid[0][2], grid[1][2], grid[2][2], grid[2][3]
        );
        //When
        List<Cell> path = bfs.findShortestPath(grid, start, end, width, height);
        //Then
        assertNotNull(path, "Алгоритм должен найти путь");
        assertEquals(expectedPath, path, "Путь, найденный алгоритмом, должен совпадать с ожидаемым");
    }

    @Test
    @DisplayName("BFS не должен найти путь в лабиринте")
    public void givenClosedFinishedPoint_whenGetBfsPath_thenBfsPathNotFind() {
        // given
        grid[1][3] = new Cell(1, 3, Type.WALL);
        grid[2][2] = new Cell(2, 2, Type.WALL);
        grid[3][3] = new Cell(3, 3, Type.WALL);
        grid[2][4] = new Cell(2, 4, Type.WALL);

        //When
        List<Cell> path = bfs.findShortestPath(grid, start, end, width, height);

        //Then
        assertEquals(new ArrayList<>(), path);
    }
}
