package generics.exercise;

interface Low {
    void c();

    void d();
}

class TopLowImpl implements Top, Low {

    @Override
    public void a() {
        System.out.println("Top::a()");
    }

    @Override
    public void b() {
        System.out.println("Top::b()");
    }

    @Override
    public void c() {
        System.out.println("Top::c()");
    }

    @Override
    public void d() {
        System.out.println("Top::d()");
    }

}

public class E25_Bounds {
    static <T extends Top> void top(T obj) {
        obj.a();
        obj.b();
    }

    static <T extends Low> void low(T obj) {
        obj.c();
        obj.d();
    }

    public static void main(String[] args) {
        TopLowImpl tli = new TopLowImpl();
        top(tli);
        low(tli);
    }
}
