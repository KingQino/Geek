package io.github.kingqino.week04.assignment;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 控制
 */
public class Solution_09 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Integer[] result = {null};
        Thread thread = new Thread(() -> {
            result[0] = fibonacci(36);
            countDownLatch.countDown();
        });
        thread.start();

        countDownLatch.await();
        System.out.println(result[0]);
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
