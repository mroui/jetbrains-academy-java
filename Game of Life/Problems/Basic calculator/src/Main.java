class Problem {

    public static void main(String[] args) {
        try {
            if (args.length == 0 || args.length > 3)
                throw new Exception("Wrong arguments amount");
            String operation = args[0];
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            System.out.println(operation.equals("+")
                    ? x + y : operation.equals("-")
                    ? x - y : operation.equals("*")
                    ? x * y : "Unknown operator");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}