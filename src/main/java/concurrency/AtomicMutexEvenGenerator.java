package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cheng Cheng
 * @date 2018-05-11 17:56
 */
public class AtomicMutexEvenGenerator extends IntGenerator {
    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        currentEvenValue.addAndGet(2);
        return 0;
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicMutexEvenGenerator());
    }
}
