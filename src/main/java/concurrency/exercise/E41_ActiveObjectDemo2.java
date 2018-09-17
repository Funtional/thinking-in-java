package concurrency.exercise;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printf;

@SuppressWarnings("Duplicates")
public class E41_ActiveObjectDemo2 {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    // Insert a random delay to produce the effect
    // of a calculation time:
    public void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                printf("starting %d + %d \n", x, y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return exec.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                printf("starting %.1f + %.1f \n", x, y);
                pause(2000);
                return x + y;
            }
        });
    }

    // Message handler without a return value:
    public void printDocument(final String s) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                print("printing document " + s);
                pause(1000);
                print("document " + s + " printed");
            }
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        E41_ActiveObjectDemo2 d1 = new E41_ActiveObjectDemo2();
        // Prevents ConcurrentModificationException:
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            results.add(d1.calculateFloat(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(d1.calculateInt(i, i));
            d1.printDocument("DOC_" + i);
        }
        print("Print all asynch call mode");

        while (results.size() > 0) {
            for (Future<?> f : results) {
                if (f.isDone()) {
                    try {
                        f.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                results.remove(f);
            }
        }

        d1.shutdown();
    }
}
