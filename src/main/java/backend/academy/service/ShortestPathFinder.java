package backend.academy.service;

import backend.academy.model.Cell;
import java.util.ArrayList;
import java.util.List;

public interface ShortestPathFinder {
    List<Cell> findShortestPath(Cell[][] grid, Cell start, Cell end, int width, int height);

    default List<Cell> getNeighbors(Cell[][] grid, Cell cell, int width, int height) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.col();
        int y = cell.row();

        if (x > 0) {
            neighbors.add(grid[y][x - 1]);
        }
        if (x < width - 1) {
            neighbors.add(grid[y][x + 1]);
        }
        if (y > 0) {
            neighbors.add(grid[y - 1][x]);
        }
        if (y < height - 1) {
            neighbors.add(grid[y + 1][x]);
        }

        return neighbors;
    }
}
