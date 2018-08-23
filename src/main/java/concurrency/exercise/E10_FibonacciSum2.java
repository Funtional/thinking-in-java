package concurrency.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Cheng Cheng
 * @date 2018-04-17 17:20
 */
class FibonacciSum2{
    private static ExecutorService exec;

    public static int fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    public static synchronized Future<Integer> runTask(final int n) {
        assert exec != null;
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += fib(i);
                }
                return sum;
            }
        });
    }

    public static synchronized void init() {
        if (exec == null) {
            exec = Executors.newCachedThreadPool();
        }
    }

    public static synchronized void shutdown() {
        if (exec != null) {
            exec.shutdown();
        }
        exec = null;
    }
}


public class E10_FibonacciSum2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> results = new LinkedList<>();
        FibonacciSum2.init();
        for (int i = 1; i <= 5; i++) {
            results.add(FibonacciSum2.runTask(i));
        }
        Thread.yield();
        FibonacciSum2.shutdown();


        for(Future<Integer> fi:results){
            System.out.println(fi.get());
        }
    }
}
