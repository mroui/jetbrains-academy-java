package tictactoe;

public class TicTacToe {

    private String cells;
    private TicTacToeResult result;
    private Boolean threeInRow;
    private Boolean twiceWins;
    private Boolean character;  //X - false, O - true

    public TicTacToe() {
        this.threeInRow = false;
        this.twiceWins = false;
        this.result = TicTacToeResult.NOT_FINISHED;
        this.character = false;
        this.cells = "         ";
    }

    public TicTacToe(String cells) {
        this();
        this.cells = cells;
    }

    public TicTacToeResult getResult() {
        return result;
    }

    public void calculate() {
        //vertically & horizontally
        for (int i = 0, j = 0; i < cells.length(); i += 3, j++) {
            if (cells.charAt(i) == cells.charAt(i + 1) && cells.charAt(i) == cells.charAt(i + 2) && cells.charAt(i) != ' ') {
                setResultByChar(cells.charAt(i));
                checkThreeInRowOrTwice();
            } else if (cells.charAt(j) == cells.charAt(j + 3) && cells.charAt(j) == cells.charAt(j + 6) && cells.charAt(j) != ' ') {
                setResultByChar(cells.charAt(j));
                checkThreeInRowOrTwice();
            }
        }
        //diagonals
        if ((cells.charAt(0) == cells.charAt(4) && cells.charAt(4) == cells.charAt(8)
                || cells.charAt(2) == cells.charAt(4) && cells.charAt(4) == cells.charAt(6)) && cells.charAt(4) != ' ') {
            setResultByChar(cells.charAt(4));
            checkThreeInRowOrTwice();
        }
        //no one win
        if (!threeInRow && !cells.contains(" ")) {
                result = TicTacToeResult.DRAW;
        }
        //more X / O than other OR twice win
        if (Math.abs(getCharCount('X') - getCharCount('O')) >= 2 || threeInRow && twiceWins) {
            result = TicTacToeResult.IMPOSSIBLE;
        }
    }

    private long getCharCount(char x) {
        return cells.chars().filter(ch -> ch == x).count();
    }

    private void setResultByChar(char ch) {
        result = ch == 'X' ? TicTacToeResult.X_WINS : TicTacToeResult.O_WINS;
    }

    private void checkThreeInRowOrTwice() {
        if (threeInRow) {
            twiceWins = true;
        } else {
            threeInRow = true;
        }
    }

    public void print() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + cells.charAt(i) + " " + cells.charAt(i + 1) + " " + cells.charAt(i + 2) + " |");
        }
        System.out.println("---------");
    }

    public boolean addCell(int x, int y) {
        int index = 0;
        if (y == 3) {
            index = x - 1;
        } else if (y == 2) {
            index = x + y;
        } else {
            index = x + 5;
        }
        if (cells.charAt(index) != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            char sign = character ? 'O' : 'X';
            cells = cells.substring(0, index) + sign + cells.substring(index + 1);
            character = !character;
            print();
        }
        return true;
    }
}
