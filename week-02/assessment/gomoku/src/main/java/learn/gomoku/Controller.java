package learn.gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.RandomPlayer;
import learn.gomoku.GameBoard;
import java.util.Scanner;


//When a class implements an interface, it inherits this data as well
// To fulfill these interface roles, a class must implement the methods prescribed by each interface by overriding the method definitions - Without these methods in place, this class will fail to compile!
public class Controller implements GameController {
    private Scanner console = new Scanner(System.in);

// build constructor
public Controller(Scanner console) {
    this.console = console;
}

// starts game. If the game is over, asks the user if they would like to play again
    @Override
    public void setUp() {
        System.out.println("Welcome to Gomoku");
        System.out.println("********************");
        System.out.println();

        do {
            play();
        } while (!playAgain());

        System.out.println("Thanks for playing! Goodbye for now!");
    }

    // set players based on user prompt
    @Override
    public Player getPlayer(Scanner console, int playerNum) {
        System.out.printf("Player %s is:%n", playerNum);
        System.out.println("Select 1 for Human Player");
        System.out.println("Select 2 for Random Player");
        System.out.print("Please Select 1 or 2: ");

        int playerSelect = Integer.parseInt(console.nextLine());
        System.out.println();

        Player player = null;

        switch(playerSelect){
            case 1:
                System.out.printf("Player %s, enter your name: ", playerNum);
                String playerName = console.nextLine();
                System.out.println();

                player = new HumanPlayer(playerName);
                break;
            case 2:
                player = new RandomPlayer();
                System.out.println("The player is " + player.getName());
                System.out.println();
                break;
            default:
            break;
        }

        return player;
    }


    @Override
    public void play() {
        // setting players from user imputs
        Player player1 = getPlayer(console, 1);
        Player player2 = getPlayer(console, 2);

        System.out.println("(Deciding who will go first...)");
        System.out.println();

        // creating new game object
        Gomoku game = new Gomoku(player1, player2);

        System.out.printf("%s goes first, make your move.%n", game.getCurrent().getName());
        System.out.println();

        GameBoard gameBoard = new GameBoard(Gomoku.WIDTH);

        // while the game is not over prompt the next turn
        while (!game.isOver()) {
            Player currentPlayer = game.getCurrent();
            System.out.printf("%s's Turn%n", currentPlayer.getName());

            Stone stone = getMove(game, currentPlayer);
            gameBoard.placeStone(stone);

            gameBoard.render();
        }

        Player winner = game.getWinner();
        // when the game is over print out the winner
        System.out.printf("%s Wins!%n", winner.getName());
        System.out.println();
    }

    // get moves and place them on board
    @Override
    public Stone getMove(Gomoku game, Player currentPlayer){
        Stone stone;
        Result result;

        // prompt the player for their move until the result is successful
        do {
            // attempt to generate a move
            stone = currentPlayer.generateMove(game.getStones());

            // human players will always return `null` for generated moves
            if (stone == null) {
                System.out.print("Enter a row: ");
                int row = Integer.parseInt(console.nextLine()) - 1;

                System.out.print("Enter a column: ");
                int column = Integer.parseInt(console.nextLine()) - 1;

                // instantiate a stone using the player's provided row and column
                stone = new Stone(row, column, game.isBlacksTurn());
            } else {
                // display automated player move (simulating that they entered their move)
                System.out.printf("Enter a row: %s%n", stone.getRow() + 1);
                System.out.printf("Enter a column: %s%n", stone.getColumn() + 1);
            }

            // place the stone in the game
            result = game.place(stone);

            // check to make sure that the result was successful
            if (!result.isSuccess()) {
                System.out.println();
                System.out.printf("[Err]: %s%n", result.getMessage());
                System.out.println();
            }
        } while (!result.isSuccess());

        return stone;
    }
    @Override
    public void printBoard() {

    }


    @Override
    public boolean playAgain() {
        System.out.print("Would you like to play again? [yes/no]: ");
        String confirmation = console.nextLine();
        System.out.println();

        // only replay if the user entered a "y" (lower or upper cased)
        return !confirmation.equalsIgnoreCase("yes");
    }
}
