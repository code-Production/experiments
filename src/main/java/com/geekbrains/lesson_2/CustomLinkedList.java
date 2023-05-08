package com.geekbrains.lesson_2;


public class CustomLinkedList<E> implements CustomList<E> {

    private Node<E> first;
    private Node<E> last;
    private int realSize = 0;

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

    public boolean add(E e) {
        return add(realSize, e);
    }

    public boolean add(int index, E e) {
        checkIfIndexIsAddable(index);
        if (index == realSize) {
            return addLast(e);
        } else {
            return addBefore(index, e);
        }
    }

    public boolean remove(E e) {
        if (e != null && realSize > 0) {
            Node<E> node = first;
            for (int i = 0; i < realSize; i++) {
                if (node.data.equals(e)) {
                    return remove(i);
                }
                node = node.next;
            }
        }
        return false;
    }

    public boolean remove(int index) {
        checkIfIndexIsGettable(index);
        Node<E> node = first;
        for (int i = 0; i < realSize; i++) {
            if (i == index) {
                //found
                if (node.prev == null) {
                    //first
                    first = node.next;
                }
                if (node.next == null) {
                    //last
                    last = node.prev;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                if (node.prev != null) {
                    node.prev.next = node.next;
                }
                realSize--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean addLast(E e) {
        if (last != null) {
            Node<E> node = new Node<>(last, e, null);
            last.next = node;
            last = node;
        } else {
            //no elements
            last = new Node<>(null, e, null);
            first = last;
        }
        realSize++;
        return true;
    }

    private boolean addBefore(int index, E e) {
        Node<E> node = first;
        for (int i = 0; i < realSize; i++) {
            if (i == index) {
                if (node.prev != null) {
                    Node<E> newNode = new Node<>(node.prev, e, node);
                    node.prev.next = newNode;
                    node.prev = newNode;
                } else {
                    Node<E> newNode = new Node<>(null, e, node);
                    node.prev = newNode;
                    first = newNode;
                }
                realSize++;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIfIndexIsGettable(index);
        Node<E> node = first;
        for (int i = 0; i < realSize; i++) {
            if (i == index) {
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (realSize > 0) {
            Node<E> node = first;
            for (int i = 0; i < realSize; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(node.data.toString());
                node = node.next;
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
