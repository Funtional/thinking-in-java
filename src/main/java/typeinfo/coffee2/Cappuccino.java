//: generics/coffee/Cappuccino.java
package typeinfo.coffee2;

public class Cappuccino extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Cappuccino> {

        @Override
        public Cappuccino create() {
            return new Cappuccino();
        }
    }

}