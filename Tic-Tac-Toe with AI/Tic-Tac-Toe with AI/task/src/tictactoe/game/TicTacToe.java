package tictactoe.game;

import tictactoe.game.player.Player;
import tictactoe.game.utils.PlayerType;
import tictactoe.game.utils.ResultType;

public class TicTacToe {

    private ResultType result;
    private final Board board;
    private Player playerX;
    private Player playerO;

    private TicTacToe() {
        result = ResultType.NOT_FINISHED;
        board = new Board();
    }

    public TicTacToe(PlayerType playerX, PlayerType playerO) {
        this();
        this.playerX = Player.create(playerX, 'X');
        this.playerO = Player.create(playerO, 'O');
    }

    public TicTacToe(String cells, PlayerType playerX, PlayerType playerO) {
        this(playerX, playerO);
        board.set(cells.replaceAll("_", " "));
    }

    public ResultType getResult() {
        return result;
    }

    private void setResultByChar(char ch) {
        result = ch == 'X' ? ResultType.X_WINS : ResultType.O_WINS;
    }

    private boolean gameOver() {
        return result != ResultType.NOT_FINISHED;
    }

    public void calculate() {
        //vertically & horizontally
        for (int i = 0, j = 0; i < board.get().length(); i += 3, j++) {
            if (board.isThreeInRow(i, i + 1, i + 2)) {
                setResultByChar(board.get().charAt(i));
                board.setThreeInRow();
            } else if (board.isThreeInRow(j, j + 3, j + 6)) {
                setResultByChar(board.get().charAt(j));
                board.setThreeInRow();
            }
        }
        //diagonals
        if (board.isThreeInDiagonals()) {
            setResultByChar(board.get().charAt(4));
            board.setThreeInRow();
        }
        //no one win
        if (!board.isThreeInRow() && board.isFilled())
            result = ResultType.DRAW;

        //more X / O than other OR twice win
        if (board.isWrong() || board.isThreeInRow() && board.isTwiceWin())
            result = ResultType.IMPOSSIBLE;
    }

    public void play() {
        while (!gameOver()) {
            makeTurn(playerX);
            if (gameOver()) break;
            makeTurn(playerO);
        }
        System.out.println(result + "\n");
    }

    private void makeTurn(Player player) {
        player.move(board);
        calculate();
    }

    public void print() {
        board.print();
    }

}
