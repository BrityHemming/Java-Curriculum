package learn.gomoku;
import learn.gomoku.game.Stone;


public class GameBoard {

    private final int width;
    private final Stone[][] board;

   // create game board object
    public GameBoard(int width) {
        this.width = width;
        this.board = new Stone[width][width];
    }

    // write method to place a stone on the board by row and column
    public void placeStone(Stone stone) {
        board[stone.getRow()][stone.getColumn()] = stone;
    }

    // render board by invoking row and column
    public void renderBoard() {
        renderColumns();
        renderRows();
    }

    private void renderColumns() {
        System.out.println();

        // add extra whitespace
        System.out.print("  ");
    // loop through all the columns and print
        for (int columnNum = 0; columnNum < width; columnNum++) {
            // print the column label
            System.out.printf(" %02d", columnNum + 1);
        }

        // print a new line at the end of the last column
        System.out.println();
    }

    private void renderRows() {
        // loop through rows and print
        for (int rowNum = 0; rowNum < width; rowNum++) {
            // print the row label
            System.out.printf("%02d", rowNum + 1);

            for (int columnNum = 0; columnNum < width; columnNum++) {
                Stone stone = board[rowNum][columnNum];
                // If there is a stone, place the stone, else place a "  _"
                if (stone != null) {
                    if(stone.isBlack()){
                        System.out.print("  X");
                    }else if(!stone.isBlack()){
                        System.out.print("  O");
                    }
                } else {
                    System.out.print("  _");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
}
