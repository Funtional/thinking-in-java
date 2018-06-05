package typeinfo;

import typeinfo.interfacea.A;

/**
 * @author Cheng Cheng
 * @date 2017-12-01 15:37
 */
public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Compile error
        System.out.println(a.getClass().getSimpleName());
        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
}

class B implements A {
    @Override
    public void f() {

    }

    public void g() {
    }
}