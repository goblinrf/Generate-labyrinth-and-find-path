package backend.academy.factory;

import backend.academy.service.MazeGenerator;
import backend.academy.service.impl.generator.KruskalMazeGenerator;
import backend.academy.service.impl.generator.PrimMazeGenerator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("LSC_LITERAL_STRING_COMPARISON")
public class MazeGeneratorFactory {

    private static final String KRUSKAL = "краскала";
    private static final String PRIM_MODIFIED = "прима (модифицированный)";

    public static MazeGenerator createMazeGenerator(String algorithm) {
        if (algorithm.equalsIgnoreCase(KRUSKAL)) {
            return new KruskalMazeGenerator();
        } else if (algorithm.equalsIgnoreCase(PRIM_MODIFIED)) {
            return new PrimMazeGenerator();
        }
        throw new IllegalArgumentException("Неизвестный алгоритм: " + algorithm);
    }

    private MazeGeneratorFactory() {
    }
}
