package backend.academy.service;

import backend.academy.model.Cell;

public interface MazeGenerator {
    void generateMaze(Cell[][] grid, int width, int height);
}
