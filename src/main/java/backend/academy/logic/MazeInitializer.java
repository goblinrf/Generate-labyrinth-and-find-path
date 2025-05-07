package backend.academy.logic;

import backend.academy.enums.State;
import backend.academy.enums.SurfaceType;
import backend.academy.enums.Type;
import backend.academy.model.Cell;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.security.SecureRandom;

@SuppressWarnings("MagicNumber")
@SuppressFBWarnings({"CLI_CONSTANT_LIST_INDEX", "DMI_RANDOM_USED_ONLY_ONCE"})
public class MazeInitializer {
    private Cell[][] grid;

    private static final int X_INDEX = 0;
    private static final int Y_INDEX = 1;

    private static final double SWAMP_CHANCE = 0.2;
    private static final double GOOD_TERRAIN_CHANCE = 0.4;

    public MazeInitializer(Cell[][] grid) {
        this.grid = grid;
    }

    public void initializeGrid(int height, int width) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    grid[y][x] = new Cell(y, x, Type.WALL, State.INTERNAL, SurfaceType.NORMAL);
                } else {
                    grid[y][x] = new Cell(y, x, Type.WALL, State.EXTERNAL, SurfaceType.NORMAL);
                }
            }
        }
    }

    public void placePoints(int[] pointA, int[] pointB) {
        if (pointA.length != 2 || pointB.length != 2) {
            throw new IllegalArgumentException("Points must have exactly two coordinates.");
        }
        int ax = pointA[X_INDEX];
        int ay = pointA[Y_INDEX];
        int bx = pointB[X_INDEX];
        int by = pointB[Y_INDEX];
        grid[ay][ax] = new Cell(ay, ax, Type.START);
        grid[by][bx] = new Cell(by, bx, Type.END);
    }

    public void setSurfaceTypes(int width, int height) {
        SecureRandom randomSurfaceType = new SecureRandom();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double randomValue = randomSurfaceType.nextDouble();
                if (randomValue < SWAMP_CHANCE) {
                    grid[y][x] = grid[y][x].withSurfaceType(SurfaceType.SWAMP);
                } else if (randomValue < GOOD_TERRAIN_CHANCE) {
                    grid[y][x] = grid[y][x].withSurfaceType(SurfaceType.GOOD_TERRAIN);
                } else {
                    grid[y][x] = grid[y][x].withSurfaceType(SurfaceType.NORMAL);
                }
            }
        }
    }
}
