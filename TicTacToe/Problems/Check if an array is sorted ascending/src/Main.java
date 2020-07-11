import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        int prev = sc.nextInt();
        prev = sc.nextInt();
        boolean flag = true;
        while (sc.hasNext()) {
            int next = sc.nextInt();
            if (prev > next) {
                flag = false;
                break;
            } else {
                prev = next;
            }
        }
        System.out.println(flag);
    }
}
