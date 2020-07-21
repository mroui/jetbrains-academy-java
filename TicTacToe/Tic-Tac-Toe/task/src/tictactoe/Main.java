package tictactoe;

import java.util.Scanner;

public class Main {

    public static boolean checkIntInput(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            //not numbers
            if (ch < 48 || ch > 57) {
                System.out.println("You should enter numbers!");
                return false;
            //must be 1-3
            } else if (ch < 49 || ch > 51) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        game.print();
        while (true) {
            System.out.print("Enter the coordinates: ");
            String xs = sc.next();
            String ys = sc.next();
            if (checkIntInput(xs) && checkIntInput(ys)) {
                if (game.addCell(Integer.parseInt(xs), Integer.parseInt(ys))) {
                    game.calculate();
                    if (game.getResult() != TicTacToeResult.NOT_FINISHED) {
                        System.out.println(game.getResult().getValue());
                        break;
                    }
                }
            }
        }
    }
}
