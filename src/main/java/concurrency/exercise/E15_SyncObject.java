package concurrency.exercise;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2018-06-04 15:06
 */
public class E15_SyncObject {
    public static void main(String[] args) throws InterruptedException {
        final SingleSynch singleSynch = new SingleSynch();
        final TripleSynch tripleSynch = new TripleSynch();

        print("Test SingleSynch...");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                singleSynch.f();
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                singleSynch.g();
            }
        };
        t2.start();
        singleSynch.h();

        t1.join(); // Wait for t1 to finish its work
        t2.join(); // Wait for t2 to finish its work

        print("Test TripleSynch...");

        new Thread() {
            @Override
            public void run() {
                tripleSynch.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                tripleSynch.g();
            }
        }.start();

        tripleSynch.h();
    }
}

class SingleSynch {
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g() {
        for (int i = 0; i < 5; i++) {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h() {
        for (int i = 0; i < 5; i++) {
            print("h()");
            Thread.yield();
        }
    }
}

class TripleSynch {
    private Object syncObjectG = new Object();
    private Object syncObjectH = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObjectG) {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }

    public void h() {
        synchronized (syncObjectH) {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        }
    }
}
