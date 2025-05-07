package backend.academy.service.impl.findpath;

import backend.academy.enums.Type;
import backend.academy.model.Cell;
import backend.academy.model.GraphNode;
import backend.academy.service.ShortestPathFinder;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

@SuppressFBWarnings("PSC_PRESIZE_COLLECTIONS")
public class AStar implements ShortestPathFinder {

    @Override
    public List<Cell> findShortestPath(Cell[][] grid, Cell start, Cell end, int width, int height) {

        PriorityQueue<GraphNode> priorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(node -> heuristic(node.cell(), end)));

        Set<Cell> closedSet = new HashSet<>();

        GraphNode startNode = new GraphNode(start, null);
        priorityQueue.add(startNode);

        Map<Cell, GraphNode> allNodes = new HashMap<>();
        allNodes.put(start, startNode);

        while (!priorityQueue.isEmpty()) {
            GraphNode current = priorityQueue.poll();

            if (current.cell().equals(end)) {
                return reconstructPath(current);
            }

            closedSet.add(current.cell());

            for (Cell neighbor : getNeighbors(grid, current.cell(), width, height)) {
                if (closedSet.contains(neighbor) || neighbor.type() == Type.WALL) {
                    continue;
                }

                if (allNodes.containsKey(neighbor)) {
                    continue;
                }

                GraphNode neighborNode = new GraphNode(neighbor, current);
                priorityQueue.add(neighborNode);
                allNodes.put(neighbor, neighborNode);
            }
        }

        return Collections.emptyList();
    }

    private List<Cell> reconstructPath(GraphNode graphNode) {
        List<Cell> path = new ArrayList<>();
        GraphNode current = graphNode;
        while (current != null) {
            path.add(current.cell());
            current = current.parent();
        }
        Collections.reverse(path);
        return path;
    }

    private int heuristic(Cell a, Cell b) {
        return Math.abs(a.col() - b.col()) + Math.abs(a.row() - b.row());
    }
}
