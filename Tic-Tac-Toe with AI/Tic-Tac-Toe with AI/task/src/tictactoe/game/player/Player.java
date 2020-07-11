package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.utils.PlayerType;

public abstract class Player {

    private final PlayerType type;
    private final char character;

    Player(PlayerType type, char character) {
        this.type = type;
        this.character = character;
    }

    public static Player create(PlayerType type, char character) {
        switch (type) {
            case USER:
                return new User(type, character);
            case EASY:
                return new AIEasy(type, character);
            case MEDIUM:
                return new AIMedium(type, character);
            case HARD:
                return new AIHard(type, character);
        }
        return null;
    }

    public PlayerType getType() {
        return type;
    }

    public char getChar() {
        return character;
    }

    void printMove() {
        if (type == PlayerType.USER)
            System.out.print("Enter the coordinates: ");
        else
            System.out.println("Making move level \"" + type + "\"");
    }

    public abstract void move(Board board);

}
