package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:53
 */
public class Manx extends Cat {
    public static class Factory implements typeinfo.factory.Factory<Manx> {
        @Override
        public Manx create() {
            return new Manx();
        }
    }
}