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

    @Override
    public boolean add(E e) {
        return add(realSize, e);
    }

    @Override
    public boolean add(int index, E e) {
        checkIfIndexIsAddable(index);
        //first node
        if (realSize == 0) {
            first = new Node<>(null, e, null);
            last = first;
            realSize++;
            System.out.println(realSize);
            return true;
        }
        if (realSize > 0) {
            //add to beginning
            if (index == 0) {
                Node<E> newNode = new Node<>(null, e, first);
                first.prev = newNode;
                first = newNode;
                realSize++;
                System.out.println(realSize);
                return true;
            }
            //add to end
            if (index == realSize) {
                Node<E> newNode = new Node<>(last, e, null);
                last.next = newNode;
                last = newNode;
                realSize++;
                System.out.println(realSize);
                return true;
            }
            //add between elements
            Node<E> node = first;
            for (int i = 0; i < realSize; i++) {
                if (i == index) {
                    Node<E> newNode = new Node<>(node.prev, e, node);
                    node.prev = newNode;
                    node.prev.next = newNode;
                    realSize++;
                    System.out.println(realSize);
                    return true;
                }
                node = node.next;
            }
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

    @Override
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

    @Override
    public boolean remove(int index) {
        checkIfIndexIsGettable(index);
        Node<E> node = first;
        for (int i = 0; i < realSize; i++) {
            if (i == index) {
                //found
                if (index == 0) {
                    //remove at beginning
                    if (realSize == 1) {
                        //remove last element
                        first = null;
                        last = null;
                    } else {
                        node.next.prev = null;
                        first = node.next;
                    }
                } else if (index == realSize - 1) {
                    //remove at end
                    node.prev.next = null;
                    last = node.prev;
                } else {
                    //removing between elements
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                realSize--;
                return true;
            }
            node = node.next;
        }

        return false;

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
