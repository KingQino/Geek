package io.github.kingqino.week04.assignment;

/**
 * 静态变量 + 主线程sleep -> 阻塞主线程
 *
 */
public class Solution_01 {

    public static int result;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

//        Runnable solution_01_task = () -> result = fibonacci(36);
        Solution_01_Task solution_01_task = new Solution_01_Task(36);

        Thread thread = new Thread(solution_01_task);

        thread.start();
        Thread.sleep(500);

        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static class Solution_01_Task extends Thread {
        int input;

        public Solution_01_Task(int input) {
            this.input = input;
        }

        public void run() {
            result = fibonacci(input);
        }

    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
