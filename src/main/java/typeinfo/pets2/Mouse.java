package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:56
 */
public class Mouse extends Rodent {
    public static class Factory implements typeinfo.factory.Factory<Mouse>{
        @Override
        public Mouse create() {
            return new Mouse();
        }
    }
}