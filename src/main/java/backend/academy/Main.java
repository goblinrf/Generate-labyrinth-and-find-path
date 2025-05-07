package backend.academy;

import backend.academy.logic.MazeController;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeController mazeController = new MazeController();
        mazeController.start();
    }
}
