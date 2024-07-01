package org.example.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class CustomHashSet implements CustomSetInterface {
    private Entry[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;

    public CustomHashSet() {
        buckets = new Entry[INITIAL_CAPACITY];
    }

    public CustomHashSet(int capacity) {
        buckets = new Entry[capacity];
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException("Can't add null to set.");
        }

        if (size / buckets.length > LOAD_FACTOR) {
            resize();
        }
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(element)) {
                return false;
            }
            current = current.next;
        }
        Entry entry = new Entry();
        entry.key = element;
        entry.next = buckets[index];
        buckets[index] = entry;
        size++;
        return true;
    }

    @Override
    public boolean contains(Object element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(Object element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(element)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new CustomIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Entry currentEntry = null;
        StringBuffer sb = new StringBuffer();

        for (int index = 0; index < buckets.length; index++) {
            if (buckets[index] != null) {
                currentEntry = buckets[index];
                sb.append("[" + index + "]");
                sb.append(" " + currentEntry.key.toString());
                while (currentEntry.next != null) {
                    currentEntry = currentEntry.next;
                    sb.append(" -> " + currentEntry.key.toString());
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    private int hashFunction(int hashCode, int bucketLength) {
        int index = hashCode;
        if (index < 0) {
            index = -index;
        }
        return index % buckets.length;
    }

    private int hashFunction(int hashCode) {
        return hashFunction(hashCode, buckets.length);
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        CustomHashSet newSet = new CustomHashSet(newCapacity);

        Stream.of(buckets)
                .filter(Objects::nonNull)
                .forEach(bucket -> {
                    for (Entry e = bucket; e != null; e = e.next) {
                        newSet.add(e.key);
                    }
                });

        buckets = newSet.buckets;
    }

    private static class Entry {
        Object key;
        Entry next;
    }

    private class CustomIterator implements Iterator {
        private int currentBucket;
        private int previousBucket;
        private Entry currentEntry;
        private Entry previousEntry;

        public CustomIterator() {
            currentBucket = -1;
            previousBucket = -1;
            currentEntry = null;
            previousEntry = null;
        }

        @Override
        public boolean hasNext() {
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }
            for (int index = currentBucket + 1; index < buckets.length; index++) {
                if (buckets[index] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Object next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;
            if (currentEntry == null || currentEntry.next == null) {
                currentBucket++;
                while (currentBucket < buckets.length && buckets[currentBucket] == null) {
                    currentBucket++;
                }
                if (currentBucket < buckets.length) {
                    currentEntry = buckets[currentBucket];
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                currentEntry = currentEntry.next;
            }
            return currentEntry.key;
        }
    }
}

