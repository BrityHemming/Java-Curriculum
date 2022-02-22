import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise11 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Swap the 2nd game with the 4th and the 3rd with the 7th.
        // 2. Print `games` and confirm their order.
    }
}
