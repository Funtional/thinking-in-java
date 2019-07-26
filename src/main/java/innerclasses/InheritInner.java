package innerclasses;

/**
 * 继承内部类注意事项
 *
 * @author Cheng Cheng
 * @date 2017-10-18 19:34
 */
public class InheritInner extends WithInner.Inner {
    public InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner);
    }
}

class WithInner {
    class Inner {

    }
}