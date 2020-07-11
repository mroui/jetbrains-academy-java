package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.TicTacToe;
import tictactoe.game.utils.PlayerType;
import tictactoe.game.utils.ResultType;

public class AIHard extends Player {

    AIHard(PlayerType type, char character) {
        super(type, character);
    }

    private int findBestMove(Board board) {
        int bestMove = -1;
        int bestValue = -10;
        for (int i = 0; i < board.get().length(); i++) {
            if (board.isEmpty(i)) {
                String trailStep = board.get().substring(0, i) + getChar() + board.get().substring(i + 1);
                int moveValue = miniMax(new Board(trailStep), 0, false);
                if (moveValue > bestValue) {
                    bestMove = i;
                    bestValue = moveValue;
                }
            }
        }
        return bestMove;
    }

    private int miniMax(Board board, int depth, boolean isMax) {
        TicTacToe simulation = new TicTacToe(board.get(), getType(), getType());
        simulation.calculate();

        if (board.isFilled())
            return 0;
        if (simulation.getResult() == ResultType.O_WINS)
            return getChar() == 'O' ? 10 : -10;
        if (simulation.getResult() == ResultType.X_WINS)
            return getChar() == 'X' ? 10 : -10;

        int best = isMax ? -10 : 10;
        for (int i = 0; i < board.get().length(); i++) {
            if (board.isEmpty(i)) {
                String cells = board.get().substring(0, i) + getChar() + board.get().substring(i + 1);
                best = Math.max(best, miniMax(new Board(cells), depth + 1, !isMax));
            }
        }
        return best;
    }

    @Override
    public void move(Board board) {
        boolean moved = false;
        while (!moved) {
            printMove();
            int index = findBestMove(board);
            moved = board.add(this, index % 3, index / 3);
        }
    }

}
