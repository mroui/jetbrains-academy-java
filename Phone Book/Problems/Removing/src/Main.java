import java.util.Arrays;
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
        private int size;
        private TableEntry<T>[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            if (isFull()) {
                rehash();
            }
            int idx = findKey(key);
            if (idx == -1 || table[idx] != null) {
                return false;
            }
            table[idx] = new TableEntry<>(key, value);
            return true;
        }

        private boolean isFull() {
            for (TableEntry<T> entry : table) {
                if (entry == null) {
                    return false;
                }
            }
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
                table[idx].removed = true;
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

        private void rehash() {
            TableEntry<T>[] copy = Arrays.copyOf(table, size);
            size *= 2;
            table = new TableEntry[size];
            for (TableEntry<T> entry : copy) {
                put(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();
            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i).append(": null");
                } else {
                    tableStringBuilder.append(i).append(": key=").append(table[i].getKey())
                            .append(", value=").append(table[i].getValue()).append(", removed=")
                            .append(table[i].isRemoved());
                }
                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }
            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        HashTable<String> hashTable = new HashTable<>(5);
        while (n > 0) {
            int key = scanner.nextInt();
            String value = scanner.next();
            hashTable.put(key, value);
            n--;
        }
        while (m > 0) {
            hashTable.remove(scanner.nextInt());
            m--;
        }
        System.out.println(hashTable.toString());
    }
}