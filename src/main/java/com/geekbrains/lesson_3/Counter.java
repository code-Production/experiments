package com.geekbrains.lesson_3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private final Lock lock;
    private int counter;

    public Counter() {
        lock = new ReentrantLock();
    }

    public void printCounter() {
        System.out.println("Counter: " + counter);
    }

    public void increment(){
        lock.lock();
        counter++;
        lock.unlock();
    }
}
