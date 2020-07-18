import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int ch = inputStream.read();
        while (ch != -1) {
            System.out.print(ch);
            ch = inputStream.read();
        }
        inputStream.close();
    }
}