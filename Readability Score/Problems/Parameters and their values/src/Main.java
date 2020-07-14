class Problem {

    public static void main(String[] args) {
        for (int i = 1; i < args.length; i += 2) {
            System.out.println(args[i - 1] + "=" + args[i]);
        }
    }
}