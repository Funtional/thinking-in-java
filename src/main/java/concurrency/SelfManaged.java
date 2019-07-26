package concurrency;

/**
 * @author Cheng Cheng
 * @date 2018-04-17 15:47
 */
public class SelfManaged implements Runnable {
    private int countDown = 5; // 倒计时
    private Thread t = new Thread(this);

    public SelfManaged() {
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), ";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SelfManaged();
        }
    }
}
