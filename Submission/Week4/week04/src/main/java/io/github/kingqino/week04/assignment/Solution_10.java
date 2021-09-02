package io.github.kingqino.week04.assignment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 */
public class Solution_10 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int result = CompletableFuture.supplyAsync(() -> fibonacci(36)).get();
        
        System.out.println(result);

    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
