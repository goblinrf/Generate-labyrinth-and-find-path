package backend.academy.service.impl.findpath;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.model.GraphNode;
import backend.academy.service.ShortestPathFinder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("ParameterAssignment")
public class BFS implements ShortestPathFinder {
    @Override
    public List<Cell> findShortestPath(Cell[][] grid, Cell start, Cell end, int width, int height) {
        Queue<GraphNode> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        GraphNode startNode = new GraphNode(start, null);
        queue.add(startNode);
        visited.add(start);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();

            if (current.cell().equals(end)) {
                return reconstructPath(current);
            }

            for (Cell neighbor : getNeighbors(grid, current.cell(), width, height)) {
                if (neighbor.type() != Type.WALL && visited.add(neighbor)) {
                    GraphNode neighborNode = new GraphNode(neighbor, current);
                    queue.add(neighborNode);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Cell> reconstructPath(GraphNode graphNode) {
        List<Cell> path = new ArrayList<>();
        while (graphNode != null) {
            path.add(graphNode.cell());
            graphNode = graphNode.parent();
        }
        Collections.reverse(path);
        return path;
    }

}
