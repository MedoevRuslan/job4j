package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleStack<T> extends DynamicLinkedList<T> {

    public T poll() {
        if (getSize() == 0) {
            throw new  NoSuchElementException();
        }
        return deleteLast();
    }

    public void push(T value) {
        add(value);
    }
}
