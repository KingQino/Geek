package io.github.kingqino.week04.assignment;

import java.util.Random;
import java.util.concurrent.*;

public class Lab {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        int timeout = 300;

        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(new MonitorKeywordService());

        executorService.execute(futureTask);

        try {
            String s = futureTask.get(timeout, TimeUnit.MILLISECONDS);
            System.out.println(s);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}

class MonitorKeywordService implements Callable<String> {

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();

        int max = 300;
        int min = 80;
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(max) % (max - min + 1) + min); // 业务代码，HTTP调关键词服务

        long end   = System.currentTimeMillis();
        return "该调用花了" + (end - start) + "ms";
    }
}
