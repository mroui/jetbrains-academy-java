package phonebook;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class HashTable<T> {
    private int size;
    private TableEntry<T>[] table;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
    }

    public void put(int key, T value) {
        if (isFull())
            rehash();
        int idx = findKey(key);
        if (idx != -1 && table[idx] == null)
            table[idx] = new TableEntry<>(key, value);
    }

    private boolean isFull() {
        for (TableEntry<T> entry : table)
            if (entry == null)
                return false;
        return true;
    }

    public T get(int key) {
        int idx = findKey(key);
        if (idx == -1 || table[idx] == null)
            return null;
        return table[idx].getValue();
    }

    private int findKey(int key) {
        int hash = key % size;
        while (!(table[hash] == null || table[hash].getKey() == key)) {
            hash = (hash + 1) % size;
            if (hash == key % size)
                return -1;
        }
        return hash;
    }

    private void rehash() {
        TableEntry<T>[] copy = Arrays.copyOf(table, size);
        size *= 2;
        table = new TableEntry[size];
        for (TableEntry<T> entry : copy)
            put(entry.getKey(), entry.getValue());
    }

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
}