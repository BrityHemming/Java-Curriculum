package learn.gomoku;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Controller gameController = new Controller(console);
        gameController.setUp();
    }
}
