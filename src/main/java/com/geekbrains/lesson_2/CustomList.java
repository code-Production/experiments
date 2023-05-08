package com.geekbrains.lesson_2;

public interface CustomList<E> {
    boolean add(E e);
    boolean add(int index, E e);
    E get(int index);
    boolean remove(E e);
    boolean remove(int index);
}
