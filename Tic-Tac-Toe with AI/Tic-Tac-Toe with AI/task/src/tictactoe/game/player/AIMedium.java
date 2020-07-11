package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.utils.PlayerType;

public class AIMedium extends Player {

    AIMedium(PlayerType type, char character) {
        super(type, character);
    }

    @Override
    public void move(Board board) {
        boolean moved = false;
        while (!moved) {
            printMove();
            int index = board.getThirdIndexTwoInRow();
            if (index != -1)
                moved = board.add(this, index % 3, index / 3);
            else
                moved = board.add(this, board.randCell(), board.randCell());
        }
    }

}
