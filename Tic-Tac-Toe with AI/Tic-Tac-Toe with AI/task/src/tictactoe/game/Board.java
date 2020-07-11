package tictactoe.game;

import tictactoe.game.player.Player;
import tictactoe.game.utils.PlayerType;

import java.util.Random;

public class Board {

    private String cells;
    private Boolean threeInRow;
    private Boolean twiceWin;

    public Board() {
        cells = "         ";
        threeInRow = false;
        twiceWin = false;
    }

    public Board(String cells) {
        this();
        this.cells = cells;
    }

    void set(String cells) {
        this.cells = cells;
    }

    public String get() {
        return cells;
    }

    Boolean isTwiceWin() {
        return twiceWin;
    }

    Boolean isThreeInRow() {
        return threeInRow;
    }

    private void setTwiceWin() {
        this.twiceWin = true;
    }

    private boolean isTwoInRow(int i, int j) {
        return cells.charAt(i) == cells.charAt(j);
    }

    boolean isThreeInRow(int i, int j, int k) {
        return isTwoInRow(i, j) && isTwoInRow(j, k) && !isEmpty(j);
    }

    boolean isThreeInDiagonals() {
        return (isTwoInRow(0, 4) && isTwoInRow(4, 8)
                || isTwoInRow(2, 4) && isTwoInRow(4, 6))
                && !isEmpty(4);
    }

    boolean isWrong() {
        return Math.abs(getCharCount('X') - getCharCount('O')) >= 2;
    }

    public boolean isFilled() {
        return !cells.contains(" ");
    }

    public boolean isEmpty(int i) {
        return cells.charAt(i) == ' ';
    }

    void setThreeInRow() {
        if (isThreeInRow())
            setTwiceWin();
        else this.threeInRow = true;
    }

    public int randCell() {
        return new Random().nextInt(3) + 1;
    }

    private long getCharCount(char x) {
        return cells.chars().filter(ch -> ch == x).count();
    }

    private int calculateIndexFromCords(int x, int y) {
        return y == 3 ? x - 1 : y == 2 ? x + y : x + 5;
    }

    public boolean add(Player player, int x, int y) {
        int index = calculateIndexFromCords(x, y);
        if (!isEmpty(index))
            if (player.getType() == PlayerType.USER) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else return add(player, randCell(), randCell());
        else {
            cells = cells.substring(0, index) + player.getChar() + cells.substring(index + 1);
            print();
        }
        return true;
    }

    public int getThirdIndexTwoInRow() {
        //vertically & horizontally
        for (int i = 0, j = 0; i < cells.length(); i += 3, j++) {
            if (isTwoInRow(i, i + 1) && !isEmpty(i))
                return i + 2;
            else if (isTwoInRow(i, i + 2) && !isEmpty(i))
                return i + 1;
            else if (isTwoInRow(i + 1, i + 2) && !isEmpty(i + 1))
                return i;
            else if (isTwoInRow(j, j + 3) && !isEmpty(j))
                return j + 6;
            else if (isTwoInRow(j, j + 6) && !isEmpty(j))
                return j + 3;
            else if (isTwoInRow(j + 3, j + 6) && !isEmpty(j + 3))
                return j;
        }
        //diagonals
        if (isTwoInRow(4, 8) && !isEmpty(4))
            return 0;
        else if (isTwoInRow(4, 6) && !isEmpty(4))
            return 2;
        else if (isTwoInRow(0, 8) && !isEmpty(0) || isTwoInRow(2, 6) && !isEmpty(2))
            return 4;
        else if (isTwoInRow(2, 4) && !isEmpty(4))
            return 6;
        else if (isTwoInRow(0, 4) && !isEmpty(4))
            return 8;
        return -1;
    }

    void print() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3)
            System.out.println("| " + cells.charAt(i) + " " + cells.charAt(i + 1) + " " + cells.charAt(i + 2) + " |");
        System.out.println("---------");
    }

}
