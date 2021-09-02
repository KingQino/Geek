package io.github.kingqino.week04.assignment;

public class RunnableTask implements Runnable {
    @Override
    public void run() {
        fibonacci(36);
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
