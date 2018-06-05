//: generics/coffee/Latte.java
package typeinfo.coffee2;


public class Latte extends Coffee {
    public static class Factory implements typeinfo.factory.Factory<Latte> {

        @Override
        public Latte create() {
            return new Latte();
        }
    }

}