package com.geekbrains.lesson_2;

import java.util.Arrays;

public class CustomArrayList<E> implements CustomList<E> {

    private Object[] DATA;
    private int realSize = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 2;

    public CustomArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public  CustomArrayList(int initialCapacity) {
        DATA = new Object[initialCapacity];
    }

    private void resizeStore(int newCapacity) {
        if (newCapacity < realSize) {
            throw new RuntimeException("Possible data loss");
        }
        if (newCapacity > realSize) {
            DATA = Arrays.copyOf(DATA, newCapacity);
        }
    }

    private void checkIfIndexIsAddable(int index) {
        if (index > realSize) {
            throw new IndexOutOfBoundsException(String.format("Index: %s, Size: %s", index, realSize));
        }
    }

    private void checkIfIndexIsGettable(int index) {
        if (index + 1 > realSize) {
            throw new IndexOutOfBoundsException(String.format("Index: %s, Size: %s", index, realSize));
        }
    }

    @Override
    public boolean add(E e) {
        return add(realSize, e);
    }

    @Override
    public boolean add(int index, E e) {
        checkIfIndexIsAddable(index);
        if (realSize + 1 > DATA.length) {
            resizeStore(DATA.length * 2);
        }
        System.arraycopy(DATA, index, DATA, index + 1, realSize - index);
        DATA[index] = e;
        realSize++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIfIndexIsGettable(index);
        return (E) DATA[index];
    }

    @Override
    public boolean remove(E e) {
        if (e != null && realSize > 0) {
            for (int i = 0; i < realSize; i++) {
                if (e.equals(DATA[i])) {
                   return remove(i);
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        checkIfIndexIsGettable(index);
        System.arraycopy(DATA, index + 1, DATA, index, realSize - index);
        realSize--;
        if (realSize < DATA.length * 0.25) {
            resizeStore(DATA.length / 2);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i < realSize; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(DATA[i].toString());
        }
        builder.append("]");
        return builder.toString();
    }
}
