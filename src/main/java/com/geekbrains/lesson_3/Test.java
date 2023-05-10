package com.geekbrains.lesson_3;

public class Test {

    public static void main(String[] args) {

        Game game = new Game();
        new Player().startGame(game::playSideA);
        new Player().startGame(game::playSideB);

        Counter counter = new Counter();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                    counter.printCounter();
                }
            }).start();
        }


    }
}
