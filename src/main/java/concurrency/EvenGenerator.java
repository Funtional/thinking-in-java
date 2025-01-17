package concurrency;


/**
 * @author Cheng Cheng
 * @date 2018-04-20 11:40
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue; // Danger point here!
        ++currentEvenValue;
        return currentEvenValue;//两次++操作后，currentEvenValue是偶数
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
