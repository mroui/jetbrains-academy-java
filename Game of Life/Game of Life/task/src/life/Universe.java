package life;

public class Universe {

    private Generation currentGen;
    private Generation nextGen;

    public Universe(int size, long seed) {
        currentGen = new Generation(size, seed);
        nextGen = currentGen.clone();
    }

    public Generation nextGen() {
        return nextGen;
    }

    public Generation currentGen() {
        return currentGen;
    }

    public void print() {
        for (boolean[] row : matrix) {
            for (int j = 0; j < matrix.length; j++)
                System.out.print(row[j] ? 'O' : ' ');
            System.out.println();
        }
    }
}
