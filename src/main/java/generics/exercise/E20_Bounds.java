package generics.exercise;

interface Top {
    void a();

    void b();
}

class CombinedImpl implements Top {
    @Override
    public void a() {
        System.out.println("Top::a()");
    }

    @Override
    public void b() {
        System.out.println("Top::b()");
    }

    public void c() {
        System.out.println("Top::c()");
    }
}

public class E20_Bounds {
    static <T extends Top> void f(T obj) {
        obj.a();
        obj.b();
        // c() is not part of an interface
        // obj.c();
    }

    public static void main(String[] args) {
        f(new CombinedImpl());
    }
}
