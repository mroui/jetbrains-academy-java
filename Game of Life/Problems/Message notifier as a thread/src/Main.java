class MessageNotifier extends Thread {

    private String message;
    private int repeats;

    public MessageNotifier(String msg, int repeats) {
        this.message = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++) {
            System.out.println(message);
        }
    }
}