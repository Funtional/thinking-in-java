package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:41
 */
public class Pug extends Dog {
    public static class Factory implements typeinfo.factory.Factory<Pug> {
        @Override
        public Pug create() {
            return new Pug();
        }
    }
}