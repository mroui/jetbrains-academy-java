package processor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix();
        m1.multiplyConstant(new Scanner(System.in).nextInt()).print();
    }
}
