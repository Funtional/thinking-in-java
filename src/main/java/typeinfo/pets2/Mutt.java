package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:40
 */
public class Mutt extends Dog {
    public static class Factory implements typeinfo.factory.Factory<Mutt>{
        @Override
        public Mutt create() {
            return new Mutt();
        }
    }
}