package io.github.kingqino.week04.assignment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future
 */
public class Solution_02 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new CallableTask(36));
        executorService.shutdown();

        int result = 0;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result );
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
