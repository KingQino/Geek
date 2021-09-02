package io.github.kingqino.week04.assignment;

/**
 * wait notify -> synchronized阻塞主线程
 */
public class Solution_05 {

    public static void main(String[] args) throws InterruptedException {
        final Integer[] result = {null};
        Thread thread = new Thread(() -> {
            synchronized (result) {
                result[0] =  fibonacci(36);
                result.notify();
            }

        });
        thread.start();

        synchronized (result) {
            result.wait();
        }

        System.out.println(result[0]);
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
