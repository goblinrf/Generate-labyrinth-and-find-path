package backend.academy.logic;

import backend.academy.factory.InputFactory;
import backend.academy.factory.MazeFindShortestPathFactory;
import backend.academy.factory.MazeGeneratorFactory;
import backend.academy.factory.OutputFactory;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import backend.academy.printer.PrintMaze;
import backend.academy.reader.ReaderMazeParams;
import backend.academy.service.InputStream;
import backend.academy.service.MazeGenerator;
import backend.academy.service.OutputStream;
import backend.academy.service.ShortestPathFinder;
import backend.academy.utils.ReadDataFromTxtFile;
import java.util.List;

@SuppressWarnings({"ConstantName", "MultipleStringLiterals"})
public class MazeController {
    InputStream input = InputFactory.createInput("console");
    ReaderMazeParams readerMazeParams = new ReaderMazeParams(input);
    OutputStream output = OutputFactory.createOutput("console");
    String algorithmMazeGeneratorPath = "src/main/resources/algorithms.txt";
    String algorithmFindPathToFile = "src/main/resources/FindPathAlgorithm.txt";

    public void start() {

        output.println(ReadDataFromTxtFile.getAlgorithmsFromFile(algorithmMazeGeneratorPath));
        String algorithmMazeGenerator = readerMazeParams.readAlgorithm(algorithmMazeGeneratorPath);
        int height = readerMazeParams.readNumber("высоту");
        int width = readerMazeParams.readNumber("ширину");
        int ax = readerMazeParams.readNumber("координату x для точки A");
        int ay = readerMazeParams.readNumber("теперь y");
        int bx = readerMazeParams.readNumber("координату x для точки B");
        int by = readerMazeParams.readNumber("теперь введите y");
        output.println(ReadDataFromTxtFile.getAlgorithmsFromFile(algorithmFindPathToFile));
        String algorithmFindPath = readerMazeParams.readAlgorithm(algorithmFindPathToFile);

        int[] pointA = {ax, ay};
        int[] pointB = {bx, by};

        MazeGenerator generator = MazeGeneratorFactory.createMazeGenerator(algorithmMazeGenerator);
        ShortestPathFinder findShortestPath = MazeFindShortestPathFactory.findShortestPath(algorithmFindPath);

        Maze maze = new Maze(height, width, pointA, pointB);
        Cell[][] grid = maze.grid();

        MazeInitializer mazeInitializer = new MazeInitializer(grid);
        mazeInitializer.initializeGrid(height, width);
        generator.generateMaze(grid, width, height);

        mazeInitializer.placePoints(pointA, pointB);
        mazeInitializer.setSurfaceTypes(width, height);
        List<Cell> path = findShortestPath.findShortestPath(grid, grid[ay][ax], grid[by][bx], width, height);

        PrintMaze printMaze = new PrintMaze(output);
        printMaze.printMaze(height, width, grid, path);
    }
}
