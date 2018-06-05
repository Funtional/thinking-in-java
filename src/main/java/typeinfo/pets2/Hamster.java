package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:57
 */
public class Hamster extends Rodent {
    public static class Factory implements typeinfo.factory.Factory<Hamster>{
        @Override
        public Hamster create() {
            return new Hamster();
        }
    }
}