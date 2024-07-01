package org.example.collection.list;

public interface CustomListInterface<E> extends Iterable<E> {
    boolean add(E element);
    E get(int index);
    void remove(int index);
    void ensureCapacity(int needCapacity);
    int size();
    boolean isEmpty();
    boolean add(int index, E element);
    int indexOf(E element);
    void set(int index, E element);
    boolean contains(E element);
    void clear();
    E[] toArray();
}
