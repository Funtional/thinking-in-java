package concurrency.exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;

/**
 * Modify Exercise 15 to use explicit Lock objects.
 * @author Cheng Cheng
 * @date 2018-06-04 15:25
 */
public class E16_ExplicitSyncObject {
    public static void main(String[] args) throws InterruptedException {
        final ExplicitSingleSynch singleSynch = new ExplicitSingleSynch();
        final ExplicitTripleSynch tripleSynch = new ExplicitTripleSynch();

        print("Test ExplicitSingleSynch...");

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

        print("Test ExplicitTripleSynch...");

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


class ExplicitSingleSynch {
    private Lock lock = new ReentrantLock();

    public void f() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void g() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void h() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitTripleSynch {
    private Lock lockF = new ReentrantLock();
    private Lock lockG = new ReentrantLock();
    private Lock lockH = new ReentrantLock();

    public void f() {
        lockF.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        } finally {
            lockF.unlock();
        }
    }

    public void g() {
        lockG.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        } finally {
            lockG.unlock();
        }
    }

    public void h() {
        lockH.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        } finally {
            lockH.unlock();
        }
    }
}
