package io.github.kingqino.week04;

import java.util.concurrent.*;

public class Shot {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });

        Future<Integer> future1 = executorService.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });


        long start = System.currentTimeMillis();
        Integer res1 = future.get(300, TimeUnit.MILLISECONDS);
        System.out.println(res1 + " - 完成第一个线程需要" + (System.currentTimeMillis() - start) + "ms");
        Integer res2 = future1.get(300, TimeUnit.MILLISECONDS);
        System.out.println(res2 + " - 完成第二个线程需要" + (System.currentTimeMillis() - start) + "ms");
        executorService.shutdown();

    }
}
