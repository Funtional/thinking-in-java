//: generics/coffee/Mocha.java
package typeinfo.coffee2;


public class Mocha extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Mocha> {

        @Override
        public Mocha create() {
            return new Mocha();
        }
    }
}
