package backend.academy.model;

import backend.academy.enums.Type;

public record Maze(Cell[][] grid) {
    private static final int X_INDEX = 0;
    private static final int Y_INDEX = 0;

    public Maze(int height, int width, int[] pointA, int[] pointB) {
        this(new Cell[height * 2 + 1][width * 2 + 1]);

        grid[pointA[X_INDEX]][pointA[Y_INDEX]] = new Cell(pointA[X_INDEX], pointA[Y_INDEX], Type.START);
        grid[pointB[X_INDEX]][pointB[Y_INDEX]] = new Cell(pointB[X_INDEX], pointB[Y_INDEX], Type.END);
    }
}
