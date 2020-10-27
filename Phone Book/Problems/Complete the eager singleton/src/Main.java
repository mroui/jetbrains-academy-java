class SimpleCounter {

    private static final SimpleCounter instance = new SimpleCounter();
    public int counter;

    private SimpleCounter() { }

    public static SimpleCounter getInstance() {
        return instance;
    }

}