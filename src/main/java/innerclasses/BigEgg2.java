package innerclasses;

/**
 * 10.10内部类可以被覆盖吗
 * @author Cheng Cheng
 * @date 2017-10-18 19:41
 */
public class BigEgg2 extends Egg2 {
    protected class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        @Override
        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();
        /*
            Egg2.Yolk()
            New Egg2()
            Egg2.Yolk()
            BigEgg2.Yolk()
            BigEgg2.Yolk.f()
         */
    }
}

class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk().f()");
        }
    }

    private Yolk y = new Yolk();

    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    public void g() {
        y.f();
    }
}