package com.geekbrains.lesson_2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(10);
        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.add(0, "st0");
//        System.out.println(linkedList);
        CustomLinkedList<String> myLinkedList = new CustomLinkedList<>();
        myLinkedList.add(0, "st0");
        myLinkedList.add(1, "st1");
        myLinkedList.add(2, "st2");
        myLinkedList.add(3, "st3");
        myLinkedList.add(4, "st4");
        myLinkedList.add(5, "st5");
        myLinkedList.add("st6");
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(6));
        myLinkedList.remove(2);
        System.out.println(myLinkedList);
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
        myLinkedList.remove(4);
        System.out.println(myLinkedList);
        myLinkedList.remove("st3");
        System.out.println(myLinkedList);
        myLinkedList.remove("st5");
        System.out.println(myLinkedList);
        myLinkedList.remove(1);
        myLinkedList.remove(0);
        System.out.println(myLinkedList);
//        myLinkedList.add(0, "st0");
//        myLinkedList.add(1, "st1");
//        myLinkedList.add(2, "st2");
//        myLinkedList.add(3, "st3");
//        myLinkedList.add(4, "st4");
//        myLinkedList.add(5, "st5");
//        myLinkedList.add("st6");
//        System.out.println(myLinkedList);
//        list.add(0, "st0");
//        list.add(1, "st1");
//        list.add(2, "st2");
//        list.add(1, "st11");
//        System.out.println(list);

        System.out.println("--------------------");
        CustomArrayList<String> myList = new CustomArrayList<>();
        myList.add(0, "st0");
        myList.add(1, "st1");
        myList.add(2, "st2");
        myList.add(3, "st3");
        myList.add(4, "st4");
        myList.add("st5");
        System.out.println(myList);
        myList.add(2, "st22");
        System.out.println(myList);
        myList.remove(6);
        System.out.println(myList);
        myList.remove("st4");
        System.out.println(myList);
        myList.remove(1);
        myList.remove(1);
        myList.remove(1);
        myList.remove(0);
        myList.remove(0);
        System.out.println(myList);
//        myList.add(0, "st0");
//        myList.add(1, "st1");
//        myList.add(2, "st2");
//        myList.add(3, "st3");
//        myList.add(4, "st4");
//        myList.add("st5");
//        System.out.println(myList);
//        System.out.println(myList.get(3));
//        System.out.println(myList.get(0));
//        System.out.println(myList.get(5));

    }
}
