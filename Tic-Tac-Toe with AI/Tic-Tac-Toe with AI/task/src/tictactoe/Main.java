package tictactoe;

import tictactoe.game.TicTacToe;
import tictactoe.game.utils.PlayerType;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String[] command = readCommand();
            if (command != null) {
                TicTacToe game = new TicTacToe(PlayerType.get(command[1]), PlayerType.get(command[2]));
                game.print();
                game.play();
            } else break;
        }
    }

    private static String[] readCommand() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command: ");
            try {
                String[] command = scanner.nextLine().split("\\s");
                if (command[0].trim().equals("start")) {
                    if (PlayerType.exists(command[1]) && PlayerType.exists(command[2]))
                        return command;
                    else throw new IllegalArgumentException();
                } else if (command[0].trim().equals("exit"))
                    return null;
                else throw new IllegalArgumentException();
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Bad parameters!");
            }
        }
    }

}
