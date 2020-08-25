package recognition;

public class Main {
    public static void main(String[] args) {
        System.out.println("This number is " + Perceptron.recognise01(Grid.read(3, 3)));
    }
}
