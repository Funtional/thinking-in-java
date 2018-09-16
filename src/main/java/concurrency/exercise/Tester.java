package concurrency.exercise;

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("Duplicates")
public abstract class Tester<C> {
    static int testReps = 10;                   // 重复次数
    static int testCycles = 100;               // 循环次数
    static int containerSize = 100;            // 容器大小

    C testContainer;                            // 容器对象
    String testId;                              // 测试ID
    int nReaders;                               // 读线程数量
    int nWriters;                               // 写线程数量
    Integer[] writerData;                       // 存放写入的数据

    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;                    // 变量作用：可以知晓所有任务何时完成

    static ExecutorService exec = Executors.newCachedThreadPool();

    /**
     * 返回将被测试的初始化后的容器
     * @return
     */
    abstract C containerInitializer();

    /**
     * 启动读取者和写入者任务，它们将读取和修改待测容器。
     */
    abstract void startReadersAndWriters();

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writerData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d\n", "readTime + writeTime =", readTime + writeTime);
        }
    }

    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResults();

        long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testReps = new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles = new Integer(args[1]);
        }
        if (args.length > 2) {
            containerSize = new Integer(args[2]);
        }
        System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write Time");
    }
}
