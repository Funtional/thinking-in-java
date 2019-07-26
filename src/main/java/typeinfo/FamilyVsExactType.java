package typeinfo;

import static net.mindview.util.Print.*;

/**
 * 14.5 instanceof与Class的等价性
 *
 * @author Cheng Cheng
 * @date 2017-11-28 18:25
 */
public class FamilyVsExactType {
    static void test(Object x) {
        print("Testing x of type " + x.getClass());
        print("x instanceof Base " + (x instanceof Base));
        print("x instanceof Derived " + (x instanceof Derived));
        print("Base.isInstance(x) " + Base.class.isInstance(x));
        print("Derived.isInstance(x) " + Derived.class.isInstance(x));
        print("x.getClass() == Base.class " + (x.getClass() == Base.class));
        print("x.getClass() == Derived.class " + (x.getClass() == Derived.class));
        print("x.getClass().equals(Base.class) " + (x.getClass().equals(Base.class)));
        print("x.getClass().equals(Derived.class) " + (x.getClass().equals(Derived.class)));
    }

    /**
     * OUTPUT:
     * Testing x of type class typeinfo.Base
     * x instanceof Base true
     * x instanceof Derived false
     * Base.isInstance(x) true
     * Derived.isInstance(x) false
     * x.getClass() == Base.class true
     * x.getClass() == Derived.class false
     * x.getClass().equals(Base.class) true
     * x.getClass().equals(Derived.class) false
     * -----------------------------------------
     * Testing x of type class typeinfo.Derived
     * x instanceof Base true
     * x instanceof Derived true
     * Base.isInstance(x) true
     * Derived.isInstance(x) true
     * x.getClass() == Base.class false
     * x.getClass() == Derived.class true
     * x.getClass().equals(Base.class) false
     * x.getClass().equals(Derived.class) true
     */
    public static void main(String[] args) {
        test(new Base());
        print("-----------------------------------------");
        test(new Derived());
    }
}

class Base {
}

class Derived extends Base {
}