import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private static class HashTable<T> {
        private final int size;
        private final TableEntry<T>[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int idx = findKey(key);
            if (idx == -1) {
                return false;
            }
            table[idx] = new TableEntry<>(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);
            if (idx == -1 || table[idx] == null) {
                return null;
            }
            return table[idx].getValue();
        }

        public void remove(int key) {
            int idx = findKey(key);
            if (idx != -1 && table[idx] != null) {
                table[idx] = null;
            }
        }

        private int findKey(int key) {
            int hash = key % size;
            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;
                if (hash == key % size) {
                    return -1;
                }
            }
            return hash;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        HashTable<String> hashTable = new HashTable<>(queries);
        while (queries > 0) {
            String command = scanner.next();
            int key = scanner.nextInt();
            switch (command) {
                case "put":
                    hashTable.put(key, scanner.next());
                    break;
                case "remove":
                    hashTable.remove(key);
                    break;
                case "get":
                    String value = hashTable.get(key);
                    System.out.println(value != null ? value : "-1");
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
            queries--;
        }
    }
}