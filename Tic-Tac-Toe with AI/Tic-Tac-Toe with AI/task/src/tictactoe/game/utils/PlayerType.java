package tictactoe.game.utils;

import java.util.Arrays;

public enum PlayerType {

    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    USER("user");

    private final String name;

    PlayerType(String val) {
        name = val;
    }

    public static PlayerType get(String name) {
        for (PlayerType p : PlayerType.values())
            if (p.name.equals(name))
                return p;
        return null;
    }

    public static boolean exists(String type) {
        return Arrays.toString(PlayerType.values()).contains(type);
    }

    @Override
    public String toString() {
        return name;
    }

}
