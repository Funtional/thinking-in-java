package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:55
 */
public class Rat extends Rodent {
    public static class Factory implements typeinfo.factory.Factory<Rat> {
        @Override
        public Rat create() {
            return new Rat();
        }
    }
}