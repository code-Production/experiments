package com.geekbrains.lesson_3;

public class Game {
    private boolean turnA = true;

    public synchronized void playSideA() {

        while(!turnA) {
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
        System.out.println("Ping");
        turnA = false;
        notify();
    }

    public synchronized void playSideB() {
        while(turnA){
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
        System.out.println("Pong");
        turnA = true;
        notify();
    }
}
