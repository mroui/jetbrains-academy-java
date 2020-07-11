class Box {
    double length;
    double height;
    double width;
    Box innerBox;
}

class Main {
    public static void main(String[] args) {
        System.out.println(new Box().innerBox);
    }
}