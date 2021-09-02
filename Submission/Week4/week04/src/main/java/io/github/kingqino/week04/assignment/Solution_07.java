package io.github.kingqino.week04.assignment;

import java.util.concurrent.Semaphore;

/**
 * Semaphore
 */
public class Solution_07 {


    public static void main(String[] args) throws InterruptedException {
        final int[] result = {0};
        Semaphore semaphore = new Semaphore(0);
        Thread thread = new Thread(() -> {

            result[0] =  fibonacci(36);
            semaphore.release();

        });
        thread.start();
        semaphore.acquire();

        System.out.println(result[0]);
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
