package tictactoe;

public enum TicTacToeResult {
    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");

    private final String value;

    TicTacToeResult(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }
}
