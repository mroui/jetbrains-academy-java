package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.utils.PlayerType;

public class AIEasy extends Player {

    AIEasy(PlayerType type, char character) {
        super(type, character);
    }

    @Override
    public void move(Board board) {
        boolean moved = false;
        while (!moved) {
            printMove();
            moved = board.add(this, board.randCell(), board.randCell());
        }
    }

}
