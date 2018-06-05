package generics;

import java.util.ArrayList;

public class ErasedTypeEquivalence {
    @SuppressWarnings("InstantiatingObjectToGetClassObject")
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}