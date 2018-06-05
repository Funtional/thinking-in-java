package concurrency;

/**
 * @author Cheng Cheng
 * @date 2018-04-25 16:49
 */
public class Atomicity {
    int i;

    void f1() {
        i++;
    }

    void f2() {
        i += 3;
    }
}
