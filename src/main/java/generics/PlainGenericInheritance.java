package generics;

class GenericSetter<T> { // Not self-bound
    void set(T arg) {
        System.out.println("GenericSetter.set(Base)");
    }
}

class DerivedGS extends GenericSetter<Base> {
    void set(Derived arg) {
        System.out.println("DerivedGS.set(Derived)");
    }
}

public class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();

        DerivedGS dgs = new DerivedGS();
        dgs.set(derived);
        dgs.set(base);  // Compiles: overloaded, not overridden!
    }
}
