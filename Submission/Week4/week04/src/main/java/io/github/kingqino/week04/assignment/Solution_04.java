package io.github.kingqino.week04.assignment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 */
public class Solution_04 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        CallableTask callableTask = new CallableTask(36);
        FutureTask<Integer> futureTask = new FutureTask<>(callableTask);

        new Thread(futureTask).start();

        int result = 0;
        try {
            result = futureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

}
