enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private int level;

    DangerLevel(int num) {
        this.level = num;
    }

    public int getLevel() {
        return this.level;
    }
}