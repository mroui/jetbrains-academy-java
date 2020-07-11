package tictactoe.game.utils;

public enum ResultType {

    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");

    private final String value;

    ResultType(String val) {
        value = val;
    }

    @Override
    public String toString() {
        return value;
    }

}
