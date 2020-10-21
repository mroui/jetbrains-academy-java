import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

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
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry<T>[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int idx = hash();
            if (idx == -1) {
                return false;
            }
            table[idx] = new TableEntry<>(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);
            return idx == -1 || table[idx] == null ? null : table[idx].getValue();
        }

        public Map<Integer, String> entrySet() {
            Map<Integer, String> keyValueSet = new HashMap<>();
            for (int i = 0; i < size; i++) {
                if (table[i] == null) {
                    continue;
                }
                Integer key = table[i].getKey();
                T value = table[i].getValue();
                if (!keyValueSet.containsKey(key)) {
                    keyValueSet.put(key, key + ": " + value);
                    continue;
                }
                String setValue = keyValueSet.get(key);
                setValue += " " + value;
                keyValueSet.replace(key, setValue);
            }
            return keyValueSet;
        }

        private int hash() {
            int hash = 0;
            while (table[hash] != null) {
                hash++;
                if (hash == size) {
                    return -1;
                }
            }
            return hash;
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
            this.size = this.size * 2;
            this.table = Arrays.copyOf(this.table, this.size);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        HashTable<String> hashTable = new HashTable<>(size);
        while (size > 0) {
            int key = scanner.nextInt();
            String value = scanner.next();
            hashTable.put(key, value);
            size--;
        }
        for (Map.Entry<Integer, String> entry : hashTable.entrySet().entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}