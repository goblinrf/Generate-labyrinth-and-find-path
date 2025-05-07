package backend.academy.service.impl.generator;

import backend.academy.enums.State;
import backend.academy.enums.SurfaceType;
import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.service.MazeGenerator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PrimMazeGenerator implements MazeGenerator {
    private final SecureRandom randomGenerator = new SecureRandom();
    private final List<Cell> boundaryCells = new ArrayList<>();

    @Override
    public void generateMaze(Cell[][] grid, int width, int height) {

        int startX = randomGenerator.nextInt(width - 2) + 1;
        int startY = randomGenerator.nextInt(height - 2) + 1;
        makeInternal(grid, startX, startY);

        while (!boundaryCells.isEmpty()) {
            Cell boundaryCell = boundaryCells.remove(randomGenerator.nextInt(boundaryCells.size()));
            List<Cell> neighbors = getInternalNeighbors(grid, boundaryCell);
            if (!neighbors.isEmpty()) {

                Cell neighbor = neighbors.get(randomGenerator.nextInt(neighbors.size()));
                removeWall(grid, boundaryCell, neighbor);
                makeInternal(grid, boundaryCell.row(), boundaryCell.col());
            }
        }
    }

    protected void removeWall(Cell[][] grid, Cell cell1, Cell cell2) {
        long wallX = (long) (cell1.col() + cell2.col()) / 2;
        long wallY = (long) (cell1.row() + cell2.row()) / 2;

        grid[(int) wallY][(int) wallX] =
            new Cell((int) wallY, (int) wallX, Type.PASSAGE, State.INTERNAL, SurfaceType.NORMAL);
    }

    protected void makeInternal(Cell[][] grid, int x, int y) {
        grid[y][x] = new Cell(y, x, Type.PASSAGE, State.INTERNAL, SurfaceType.NORMAL);
        addBoundaryNeighbors(grid, x, y);
    }

    protected void addBoundaryNeighbors(Cell[][] grid, int x, int y) {
        addBoundaryCell(grid, x - 2, y);  // Слева
        addBoundaryCell(grid, x + 2, y);  // Справа
        addBoundaryCell(grid, x, y - 2);  // Сверху
        addBoundaryCell(grid, x, y + 2);  // Снизу
    }

    private void addBoundaryCell(Cell[][] grid, int x, int y) {
        if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length && grid[y][x]
            != null && grid[y][x].state() == State.EXTERNAL) {
            grid[y][x] = new Cell(y, x, Type.WALL, State.BOUNDARY, SurfaceType.NORMAL);
            boundaryCells.add(grid[y][x]);
        }
    }

    private List<Cell> getInternalNeighbors(Cell[][] grid, Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        addInternalNeighbor(grid, neighbors, cell.col() - 2, cell.row());
        addInternalNeighbor(grid, neighbors, cell.col() + 2, cell.row());
        addInternalNeighbor(grid, neighbors, cell.col(), cell.row() - 2);
        addInternalNeighbor(grid, neighbors, cell.col(), cell.row() + 2);
        return neighbors;
    }

    private void addInternalNeighbor(Cell[][] grid, List<Cell> neighbors, int x, int y) {
        if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length && grid[y][x] != null
            && grid[y][x].state() == State.INTERNAL) {
            neighbors.add(grid[y][x]);
        }
    }
}
