package backend.academy.logic;

import backend.academy.enums.SurfaceType;
import backend.academy.enums.Type;
import backend.academy.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeInitializerTest {
    private Cell[][] grid;
    private MazeInitializer mazeInitializer;
    private final int width = 5;
    private final int height = 5;

    @BeforeEach
    public void setUp() {
        grid = new Cell[height][width];
        mazeInitializer = new MazeInitializer(grid);
    }

    @Test
    @DisplayName("Инициализация: на границах сетки должны быть установлены стены")
    public void givenGridDimensions_whenInitializeGrid_thenWallsOnBorders() {
        // When
        mazeInitializer.initializeGrid(height, width);

        // Then
        for (int x = 0; x < width; x++) {
            assertEquals(Type.WALL, grid[0][x].type(), "Должна быть стена на верхней границе.");
            assertEquals(Type.WALL, grid[height - 1][x].type(), "Должна быть стена на нижней границе.");
        }
        for (int y = 0; y < height; y++) {
            assertEquals(Type.WALL, grid[y][0].type(), "Должна быть стена на левой границе.");
            assertEquals(Type.WALL, grid[y][width - 1].type(), "Должна быть стена на правой границе.");
        }
    }

    @Test
    @DisplayName("Установка начальной и конечной точек")
    public void givenTwoPoints_whenPlacePoints_thenStartAndEndAreSet() {
        // Given
        int[] pointA = {1, 1};
        int[] pointB = {3, 3};

        // When
        mazeInitializer.placePoints(pointA, pointB);

        // Then
        assertEquals(Type.START, grid[1][1].type(), "Должна быть установлена стартовая точка.");
        assertEquals(Type.END, grid[3][3].type(), "Должна быть установлена конечная точка.");
    }

    @Test
    @DisplayName("Ошибка при установке точек: точки имеют некорректные координаты")
    public void givenInvalidPointArrays_whenPlacePoints_thenThrowException() {
        // Given
        int[] invalidPointA = {1};
        int[] pointB = {3, 3};

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mazeInitializer.placePoints(invalidPointA, pointB);
        });

        // Then
        assertEquals("Points must have exactly two coordinates.", exception.getMessage());
    }

    @Test
    @DisplayName("Тип поверхности ячеек: должны быть установлены корректные типы поверхности")
    public void givenGrid_whenSetSurfaceTypes_thenCellsHaveValidSurfaceTypes() {
        // Given
        mazeInitializer.initializeGrid(height, width);

        // When
        mazeInitializer.setSurfaceTypes(width, height);

        // Then
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                SurfaceType surfaceType = grid[y][x].surfaceType();
                assertTrue(surfaceType == SurfaceType.NORMAL || surfaceType == SurfaceType.GOOD_TERRAIN ||
                        surfaceType == SurfaceType.SWAMP,
                    "Тип поверхности должен быть одним из: NORMAL, GOOD_TERRAIN или SWAMP.");
            }
        }
    }
}
