package data_structures.table;

import java.util.*;

public abstract class Hashtable<K, V> {

    private static class Entry<K, V> implements Map.Entry<K, V> {
        final int hash;
        private final K key;
        private V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            if (value == null)
                throw new NullPointerException();

            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;

            return (key == null ? e.getKey() == null : key.equals(e.getKey())) &&
                    (value == null ? e.getValue() == null : value.equals(e.getValue()));
        }

        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    private final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private final double loadFactor = 0.75;

    private int size = 0;
    private Entry<?, ?>[] table;

    public Hashtable(int capacity) {
        table = new Entry<?, ?>[capacity];
    }

    public Hashtable() {
        this(11);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        Entry<?, ?>[] tab = table;
        int hash = hash(key.hashCode());
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry<?, ?> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        return contains(value);
    }

    public boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Entry<?, ?>[] tab = table;
        for (int i = tab.length; i-- > 0;) {
            for (Entry<?, ?> e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        Entry<?, ?>[] tab = table;
        int hash = hash(key.hashCode());
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry<?, ?> e = tab[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return (V) e.value;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Entry<?, ?>[] tab = table;
        int hash = hash(key.hashCode());
        int index = (hash & 0x7FFFFFFF) % tab.length;
        Entry<K, V> entry = (Entry<K, V>) tab[index];
        for (; entry != null; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                V old = entry.value;
                entry.value = value;
                return old;
            }
        }
        addEntry(hash, key, value, index);
        return null;
    }

    public V remove(Object key) {
        Entry<?, ?>[] tab = table;
        int hash = hash(key.hashCode());
        int index = (hash & 0x7FFFFFFF) % tab.length;

        @SuppressWarnings("unchecked")
        Entry<K, V> e = (Entry<K, V>) tab[index];
        for (Entry<K, V> prev = null; e != null; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                size--;
                V oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> t) {
        for (Map.Entry<? extends K, ? extends V> e : t.entrySet())
            put(e.getKey(), e.getValue());
    }

    public void clear() {
        Entry<?, ?>[] tab = table;
        for (int index = tab.length; --index >= 0;)
            tab[index] = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Entry<?, ?> entry : table) {
            Entry<K, V> e = (Entry<K, V>) entry;
            while (e != null) {
                keys.add(e.getKey());
                e = e.next;
            }
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<?, ?> entry : table) {
            Entry<K, V> e = (Entry<K, V>) entry;
            while (e != null) {
                values.add(e.getValue());
                e = e.next;
            }
        }
        return values;
    }

    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (Entry<?, ?> entry : table) {
            Entry<K, V> e = (Entry<K, V>) entry;
            while (e != null) {
                entries.add(e);
                e = e.next;
            }
        }
        return entries;
    }

    public abstract int hash(int hashCode);

    @SuppressWarnings("unchecked")
    private void addEntry(int hash, K key, V value, int index) {
        Entry<?, ?>[] tab = table;
        if (size >= threshold()) {
            rehash();

            tab = table;
            hash = hash(key.hashCode());
            index = (hash & 0x7FFFFFFF) % tab.length;
        }
        Entry<K, V> e = (Entry<K, V>) tab[index];

        tab[index] = new Entry<>(hash, key, value, e);
        size++;
    }

    private int threshold() {
        return (int) Math.min(table.length * loadFactor, MAX_ARRAY_SIZE + 1);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int oldCap = table.length;
        Entry<?, ?>[] oldMap = table;

        int newCap = (oldCap << 1) + 1;
        if (newCap - MAX_ARRAY_SIZE > 0) {
            if (oldCap == MAX_ARRAY_SIZE)
                return;
            newCap = MAX_ARRAY_SIZE;
        }
        Entry<?, ?>[] newMap = new Entry<?, ?>[newCap];

        table = newMap;

        for (int i = oldCap; i-- > 0;) {
            for (Entry<K, V> old = (Entry<K, V>) oldMap[i]; old != null; old = old.next) {
                Entry<K, V> e = old;

                int index = (e.hash & 0x7FFFFFFF) % newCap;
                e.next = (Entry<K, V>) newMap[index];
                newMap[index] = e;
            }
        }
    }
}
