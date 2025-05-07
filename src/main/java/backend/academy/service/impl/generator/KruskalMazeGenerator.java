package backend.academy.service.impl.generator;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.model.Edge;
import backend.academy.service.MazeGenerator;
import backend.academy.service.impl.generator.helper.UnionFind;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMazeGenerator implements MazeGenerator {
    private List<Edge> edges;
    private UnionFind unionFind;

    @Override
    public void generateMaze(Cell[][] grid, int width, int height) {
        edges = new ArrayList<>();
        unionFind = new UnionFind(width * height);

        generateEdges(width, height);
        Collections.shuffle(edges);

        for (Edge edge : edges) {
            int cell1Index = edge.y1 * width + edge.x1;
            int cell2Index = edge.y2 * width + edge.x2;

            if (unionFind.find(cell1Index) != unionFind.find(cell2Index)) {
                unionFind.union(cell1Index, cell2Index);
                removeWall(grid, edge);
            }
        }
    }

    protected void generateEdges(int width, int height) {
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (x < width - 1) {
                    edges.add(new Edge(x, y, x + 1, y));  // Ребро вправо
                }
                if (y < height - 1) {
                    edges.add(new Edge(x, y, x, y + 1));  // Ребро вниз
                }
            }
        }
    }

    protected void removeWall(Cell[][] grid, Edge edge) {
        grid[0][0] = new Cell(0, 0, Type.PASSAGE);
        if (edge.x1 != edge.x2) {
            long wallX = (long) (edge.x1 + edge.x2) / 2; // Изменено на long
            grid[edge.y1][(int) wallX] = new Cell(edge.y1, (int) wallX, Type.PASSAGE);
        }

        if (edge.y1 != edge.y2) {
            long wallY = (long) (edge.y1 + edge.y2) / 2;
            grid[(int) wallY][edge.x1] = new Cell((int) wallY, edge.x1, Type.PASSAGE);
        }
    }
}
