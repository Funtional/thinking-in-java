package typeinfo.pets2;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 15:43
 */
public class EgyptianMau extends Cat {
    public static class Factory implements typeinfo.factory.Factory<EgyptianMau> {

        @Override
        public EgyptianMau create() {
            return new EgyptianMau();
        }
    }

}