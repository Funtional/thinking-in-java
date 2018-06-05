package typeinfo.pets2;


/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:54
 */
public class Cymric extends Manx {
    public static class Factory implements typeinfo.factory.Factory<Cymric> {

        @Override
        public Cymric create() {
            return new Cymric();
        }
    }
}