package org.example.set;

import java.util.Iterator;

public interface CustomSetInterface {
    boolean add(Object element);
    boolean contains(Object element);
    boolean remove(Object element);
    Iterator iterator();
    int size();
}

