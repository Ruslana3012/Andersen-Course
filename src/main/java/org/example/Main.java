package org.example;

import org.example.list.CustomArrayList;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        System.out.println("Checking [CustomArrayList]: \nWe anticipate that the size of the list will be [0]: " + list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("We anticipate the list of [1, 2, 3] after added: ");
        list.forEach(System.out::print);
        System.out.println("\nWe attempt to retrieve the value [2] at index 1 using the get() method: " + list.get(1));
        list.remove(1);
        System.out.println("We anticipate the list of [1, 3] after using the remove() method: ");
        list.forEach(System.out::print);

        Set<Integer> set = new HashSet<>();
        System.out.println("\n\nChecking [CustomArrayList]: \nWe anticipate that the size of the set will be [0]: " + set.size());
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println("We anticipate that the size of the set will be[3]: " + set.size());
        System.out.println("We anticipate [true] using contains(2) method: " + set.contains(2));
        set.remove(3);
        System.out.println("We anticipate that the size of the set will be[2]: " + set.size());
    }
}