package arrays.exercise;

import java.util.ArrayList;

/**
 * @author Cheng Cheng
 * @date 2017-12-07 10:38
 */
public class E09_PeelBanana {
    public static void main(String[] args) {
        // Compile error:
        //! Peel<Banana> a=new Peel<Banana>[10];
        ArrayList<Peel<Banana>> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(new Peel<>(new Banana(i)));
        }
        for (Peel<Banana> p : a) {
            p.peel();
        }
    }
}

class Banana {
    private final int id;

    Banana(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class Peel<T> {
    T fruit;

    Peel(T fruit) {
        this.fruit = fruit;
    }

    void peel() {
        System.out.println("Peeling " + fruit);
    }
}