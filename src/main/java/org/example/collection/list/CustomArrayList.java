package org.example.collection.list;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomListInterface<E> {
    private E[] elements;
    private int size;

    public CustomArrayList() {
        this(10);
    }

    public CustomArrayList(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("Illegal size: " + initSize);
        }
        this.elements = (E[]) new Object[initSize];
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(this.size + 1);
        elements[this.size++] = element;
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return this.elements[index];
    }

    @Override
    public void remove(int index) {
        int movedNumber = this.size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, movedNumber);
        }
        this.elements[--this.size] = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void ensureCapacity(int needCapacity) {
        if (needCapacity > elements.length) {
            Object[] oldElements = this.elements;
            int newSize = this.size * 2 + 1;
            this.elements = (E[]) Arrays.copyOf(oldElements, newSize);
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index >= this.size + 1) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        ensureCapacity(this.size + 1);
        System.arraycopy(this.elements, index, elements, index + 1, size - index);
        elements[index] = element;
        this.size++;
        return true;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.elements.length; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void set(int index, E element) {
        if (index < 0 || index >= this.size + 1) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        ensureCapacity(this.size + 1);
        this.elements[index] = element;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            for (E e : this.elements) {
                if (e == null) {
                    return true;
                }
            }
        } else {
            for (E e : this.elements) {
                if (element.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E[] toArray() {
        return this.elements;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    private class CustomIterator<E> implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public E next() {
            E value = (E) elements[current++];
            return value;
        }
    }
}
