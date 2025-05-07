package backend.academy.factory;

import backend.academy.service.ShortestPathFinder;
import backend.academy.service.impl.findpath.AStar;
import backend.academy.service.impl.findpath.BFS;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("LSC_LITERAL_STRING_COMPARISON")
public class MazeFindShortestPathFactory {

    private static final String A_STAR = "a-star";
    private static final String BFS = "bfs";

    public static ShortestPathFinder findShortestPath(String finderAlgorithm) {
        if (finderAlgorithm.equalsIgnoreCase(A_STAR)) {
            return new AStar();
        }
        if (finderAlgorithm.equalsIgnoreCase(BFS)) {
            return new BFS();
        }
        throw new IllegalArgumentException("Unknown algorithm: " + finderAlgorithm);
    }

    private MazeFindShortestPathFactory() {
    }
}
