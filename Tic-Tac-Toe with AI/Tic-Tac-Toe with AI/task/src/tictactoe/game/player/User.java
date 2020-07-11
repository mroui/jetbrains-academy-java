package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.utils.PlayerType;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

class User extends Player {

    User(PlayerType type, char character) {
        super(type, character);
    }

    private String[] checkCords(String cords) {
        try {
            String[] separatedCords = cords.split("\\s");
            int x = Integer.parseInt(separatedCords[0]);
            int y = Integer.parseInt(separatedCords[1]);
            if (x < 1 || x > 3 || y < 1 || y > 3) throw new IllegalArgumentException();
            return separatedCords;
        } catch (PatternSyntaxException | IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }
    }

    @Override
    public void move(Board board) {
        boolean moved = false;
        while (!moved) {
            printMove();
            String[] cords = checkCords(new Scanner(System.in).nextLine());
            if (cords != null)
                moved = board.add(this, Integer.parseInt(cords[0]), Integer.parseInt(cords[1]));
        }
    }

}
