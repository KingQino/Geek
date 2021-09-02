package io.github.kingqino.week04.assignment;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    private final int input;

    public CallableTask(int input) {
        this.input = input;
    }
    @Override
    public Integer call() throws Exception {

        return fibonacci(this.input);
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
