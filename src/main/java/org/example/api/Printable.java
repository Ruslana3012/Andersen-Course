package org.example.api;

public interface Printable {
    default void print() {
        System.out.println("Print content in console");
    }
}
