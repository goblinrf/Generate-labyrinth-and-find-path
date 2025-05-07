package backend.academy.service.impl.generator;

import backend.academy.enums.State;
import backend.academy.enums.SurfaceType;
import backend.academy.enums.Type;
import backend.academy.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PrimMazeGeneratorTest {

    @InjectMocks
    private PrimMazeGenerator primMazeGenerator;

    private Cell[][] grid;
    private int width = 5;
    private int height = 5;

    @BeforeEach
    public void setUp() {
        // Инициализация сетки лабиринта
        grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, Type.WALL, State.EXTERNAL, SurfaceType.NORMAL);
            }
        }
    }

    @Test
    @DisplayName("Удаление стены между соседними ячейками должно работать корректно")
    public void givenAdjacentCells_whenRemoveWallBetweenThem_thenCellInBetweenShouldBePassage() {
        // given
        Cell cell1 = new Cell(1, 1, Type.PASSAGE, State.INTERNAL, SurfaceType.NORMAL);
        Cell cell2 = new Cell(1, 3, Type.PASSAGE, State.INTERNAL, SurfaceType.NORMAL);
        grid[1][1] = cell1;
        grid[1][3] = cell2;

        // when
        primMazeGenerator.removeWall(grid, cell1, cell2);

        // then
        assertEquals(Type.PASSAGE, grid[1][2].type(), "Должна быть удалена стена между соседними ячейками");
    }

    @Test
    @DisplayName("Добавление граничных клеток должно корректно работать")
    public void givenInternalCell_whenAddBoundaryNeighbors_thenNeighborsShouldBeBoundary() {
        // given
        primMazeGenerator.makeInternal(grid, 2, 2);

        // when
        primMazeGenerator.addBoundaryNeighbors(grid, 2, 2);

        // then
        assertEquals(State.BOUNDARY, grid[0][2].state(), "Клетка сверху должна быть помечена как boundary");
        assertEquals(State.BOUNDARY, grid[4][2].state(), "Клетка снизу должна быть помечена как boundary");
        assertEquals(State.BOUNDARY, grid[2][0].state(), "Клетка слева должна быть помечена как boundary");
        assertEquals(State.BOUNDARY, grid[2][4].state(), "Клетка справа должна быть помечена как boundary");
    }
}
