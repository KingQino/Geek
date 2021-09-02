package io.github.kingqino.week04.assignment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock Condition
 */
public class Solution_06 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(Solution_06::modify).start();
        try {
            lock.lock();
            condition.await();
            System.out.println(result);
        } finally {
            lock.unlock();
        }
    }

    final static Lock lock = new ReentrantLock();
    final static Condition condition  = lock.newCondition();

    private static int result = 0;

    public static void modify() {
        try {
            lock.lock();
            result = fibonacci(36);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static int get() {

        return result;
    }

    private static int fibonacci(int a) {
        if (a < 2) return 1;

        return fibonacci(a - 1) + fibonacci(a - 2);
    }
}
