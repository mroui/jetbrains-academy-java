package processor;

public class Main {

    public static void main(String[] args) {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        Matrix m3 = m1.addMatrix(m2);
        if (m3 != null)
            m3.print();
    }
}
