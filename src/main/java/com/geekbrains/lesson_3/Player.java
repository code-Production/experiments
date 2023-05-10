package com.geekbrains.lesson_3;

public class Player {

    public void startGame(Runnable action) {
        new Thread(() -> {
            while(!Thread.interrupted()) {
                action.run();
            }
        }).start();
    }

}
