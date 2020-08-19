import java.util.*;

interface Multiset<E> {

    void add(E elem);

    void remove(E elem);

    void union(Multiset<E> other);

    void intersect(Multiset<E> other);

    int getMultiplicity(E elem);

    boolean contains(E elem);

    int numberOfUniqueElements();

    int size();

    Set<E> toSet();
}

class HashMultiset<E> implements Multiset<E> {

    private Map<E, Integer> map = new HashMap<>();

    /**
     * Add an element to the multiset.
     * It increases the multiplicity of the element by 1.
     */
    @Override
    public void add(E elem) {
        if (map.containsKey(elem)) {
            map.replace(elem, map.get(elem) + 1);
        } else {
            map.put(elem, 1);
        }
    }

    /**
     * Remove an element from the multiset.
     * It decreases the multiplicity of the element by 1.
     */
    @Override
    public void remove(E elem) {
        if (map.containsKey(elem)) {
            map.replace(elem, map.get(elem) - 1);
            if (map.get(elem) <= 0) {
                map.remove(elem);
            }
        }
    }

    /**
     * Unite this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in at least one of the initial multisets.
     * The multiplicity of each element is equal to the maximum multiplicity of
     * the corresponding elements in both multisets.
     */
    @Override
    public void union(Multiset<E> other) {
        for (E elem : other.toSet()) {
            map.put(elem, Math.max(getMultiplicity(elem), other.getMultiplicity(elem)));
        }
    }

    /**
     * Intersect this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in the both multisets.
     * The multiplicity of each element is equal to the minimum multiplicity of
     * the corresponding elements in the intersecting multisets.
     */
    @Override
    public void intersect(Multiset<E> other) {
        Map<E, Integer> newSet = new HashMap<>();
        for (E elem : other.toSet()) {
            if (map.containsKey(elem)) {
                newSet.put(elem, Math.min(getMultiplicity(elem), other.getMultiplicity(elem)));
            }
        }
        map = newSet;
    }

    /**
     * Returns multiplicity of the given element.
     * If the set doesn't contain it, the multiplicity is 0
     */
    @Override
    public int getMultiplicity(E elem) {
        return map.getOrDefault(elem, 0);
    }

    /**
     * Check if the multiset contains an element,
     * i.e. the multiplicity > 0
     */
    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem);
    }

    /**
     * The number of unique elements,
     * that is how many different elements there are in a multiset.
     */
    @Override
    public int numberOfUniqueElements() {
        return toSet().size();
    }

    /**
     * The size of the multiset, including repeated elements
     */
    @Override
    public int size() {
        int size = 0;
        for (Integer i : map.values()) {
            size += i;
        }
        return size;
    }

    /**
     * The set of unique elements (without repeating)
     */
    @Override
    public Set<E> toSet() {
        // Creating a new HashSet<> object helps us avoid ConcurrentModificationException.
        // It is thrown when we try to iterate over elements of Map and modify them at the same time
        return new HashSet<>(map.keySet());
    }
}