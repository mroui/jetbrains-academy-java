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

    public void generate(int epoch) {
        Generation newGen = new Generation(new boolean[nextGen.getSize()][nextGen.getSize()]);
        while (epoch >= 0 && epoch != 0) {
            for (int i = 0; i < newGen.getSize(); i++) {
                for (int j = 0; j < newGen.getSize(); j++) {
                    int neighbours = nextGen.countNeighbours(i, j);
                    if ((neighbours < 2 || neighbours > 3) && nextGen.isCellAlive(i, j))
                        newGen.get()[i][j] = false;
                    else if (neighbours == 3 && !nextGen.isCellAlive(i, j))
                        newGen.get()[i][j] = true;
                    else
                        newGen.get()[i][j] = nextGen.get()[i][j];
                }
            }
            currentGen = nextGen.clone();
            nextGen = newGen.clone();
            epoch--;
        }
    }
}
