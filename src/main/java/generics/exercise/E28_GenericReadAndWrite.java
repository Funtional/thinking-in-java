package generics.exercise;

import typeinfo.pets.Cat;
import typeinfo.pets.Mouse;
import typeinfo.pets.Pet;
import typeinfo.pets.Rodent;

class Generic1<T> {
    public void set(T arg) {
    }
}

class Generic2<T> {
    public T get() {
        return null;
    }
}

public class E28_GenericReadAndWrite {
    /**
     * <? super T>是逆变
     *
     * @param obj
     * @param item
     * @param <T>
     */
    static <T> void f1(Generic1<? super T> obj, T item) {
        obj.set(item);
    }

    /**
     * <? extends T>是协变
     *
     * @param obj
     * @param <T>
     */
    static <T> T f2(Generic2<? extends T> obj) {
        return obj.get();
    }

    public static void main(String[] args) {
        Generic1<Rodent> g1 = new Generic1<Rodent>();
        f1(g1, new Mouse()); // OK
        // Compile error: Cat is not a Rodent
        // f1(g1, new Cat());

        Generic2<Pet> g2 = new Generic2<Pet>();
        Pet pet = f2(g2);   // OK
        Generic2<Cat> g3 = new Generic2<Cat>();
        Cat cat = f2(g3);   // OK
        pet = f2(g3);       // Ok
    }
}
