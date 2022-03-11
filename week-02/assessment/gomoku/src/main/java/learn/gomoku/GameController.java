package learn.gomoku;
// importing players
import learn.gomoku.game.Gomoku;
import learn.gomoku.players.Player;
import learn.gomoku.game.Stone;
import java.util.Scanner;

// an interface defines a job. It's a collection of method names - these methods must be performed as part of the job defined by the interface
//The interface itself has no clue how (or which) classes will ever implement its requirements, but it promises they will (somehow).
//To fulfill these interface roles, a class must implement the methods prescribed by each interface by overriding the method definitions
public interface GameController {

    void setUp();
    Player getPlayer(Scanner console, int playerNum);
    void play();
    boolean playAgain();
    public Stone getMove(Gomoku game, Player currentPlayer);

}
