package generics.exercise;

abstract class GenericProcessor<T extends GenericProcessor<T>> {
    abstract T process(T arg);

    T test() {
        return process(null);
    }
}

class ConcreteProcessor extends GenericProcessor<ConcreteProcessor> {
    @Override
    ConcreteProcessor process(ConcreteProcessor arg) {
        if (arg == null) {
            return this;
        }
        return arg;
    }
}

public class E34_SelfBounded {
    public static void main(String[] args) {
        ConcreteProcessor cp = new ConcreteProcessor();
        System.out.println(cp == cp.test());
    }
}