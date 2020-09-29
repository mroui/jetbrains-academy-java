import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        try {
            Cinema c = Cinema.read();
            if (c != null) {
                System.out.println(c.getAvailableRowForSeats() + "");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

class Cinema {

    private final int seatsInRow;
    private final int rows;
    private final boolean[][] seats;
    private final int neededSeats;

    public Cinema(boolean[][] seats, int neededSeats) {
        this.seats = seats.clone();
        rows = seats.length;
        seatsInRow = seats.length > 0 ? seats[0].length : 0;
        this.neededSeats = neededSeats;
    }

    public static Cinema read() {
        try (Scanner scanner = new Scanner(System.in)) {
            int r = scanner.nextInt();
            int s = scanner.nextInt();
            boolean[][] seats = new boolean[r][s];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < s; j++) {
                    seats[i][j] = scanner.nextInt() == 1;
                }
            }
            int neededSeats = scanner.nextInt();
            return new Cinema(seats, neededSeats);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public int getAvailableRowForSeats() {
        int needsTemp = neededSeats;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                needsTemp = !seats[i][j] ? needsTemp - 1 : neededSeats;
                if (needsTemp == 0) {
                    return i + 1;
                }
            }
            needsTemp = neededSeats;
        }
        return 0;
    }
}