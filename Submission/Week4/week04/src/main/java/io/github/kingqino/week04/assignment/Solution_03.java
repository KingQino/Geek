package io.github.kingqino.week04.assignment;

/**
 * 静态变量 + join() -> 阻塞主线程
 * 两种线程实现方式 x2
 */
public class Solution_03 {

    public static int result;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Runnable solution_03_task = () -> result = fibonacci(36);
//        Solution_03_Task solution_03_task = new Solution_03_Task(36);

        Thread thread = new Thread(solution_03_task);

        thread.start();
        thread.join();

        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static class Solution_03_Task extends Thread {
        int input;

        public Solution_03_Task(int input) {
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
