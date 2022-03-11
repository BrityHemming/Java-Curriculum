package learn.gomoku;
import learn.gomoku.game.Stone;
import java.util.Scanner;

public class GameBoard {
    private final int boardWidth;
    private final Stone[][] board;

    /**
     * Creates a game board with the provided width.
     * @param boardWidth
     */
    public GameBoard(int boardWidth) {
        this.boardWidth = boardWidth;
        this.board = new Stone[boardWidth][boardWidth];
    }

    /**
     * Places a stone on the game board.
     * @param stone The stone to place.
     */
    public void placeStone(Stone stone) {
        board[stone.getRow()][stone.getColumn()] = stone;
    }

    /**
     * Renders the game board.
     */
    public void render() {
        renderColumnLabels();
        renderRows();
    }

    private void renderColumnLabels() {
        System.out.println();

        // add extra whitespace for the first column to make room for the row labels
        System.out.print("  ");

        for (int columnIndex = 0; columnIndex < boardWidth; columnIndex++) {
            // print the column label
            System.out.printf(" %02d", columnIndex + 1);
        }

        // print a new line at the end of the last column
        System.out.println();
    }

    private void renderRows() {
        // print rows
        for (int rowIndex = 0; rowIndex < boardWidth; rowIndex++) {
            // print the row label
            System.out.printf("%02d", rowIndex + 1);

            for (int columnIndex = 0; columnIndex < boardWidth; columnIndex++) {
                Stone stone = board[rowIndex][columnIndex];

                if (stone != null) {
                    System.out.printf("  %s", stone.isBlack() ? 'X' : 'O');
                } else {
                    System.out.print("  _");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
}
