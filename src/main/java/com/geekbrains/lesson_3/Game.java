package com.geekbrains.lesson_3;

public class Game {

    private boolean turn = true;

    public synchronized void playSide(boolean side) {

        while(turn != side) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (turn) {
            System.out.println(Thread.currentThread().getName() + ": Ping");
        } else {
            System.out.println(Thread.currentThread().getName() + ": Pong");
        }
        turn = !turn;
        notify();
    }
}
