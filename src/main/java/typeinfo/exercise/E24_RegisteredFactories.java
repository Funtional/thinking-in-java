package typeinfo.exercise;

import net.mindview.util.Null;
import typeinfo.factory.Factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Cheng Cheng
 * @date 2017-11-28 16:03
 */
public class E24_RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            IPart part = Part3.createRandom();
            // Real object
            System.out.println(part);
            // Null companion
            System.out.println(Part3.newNull(part.getClass()));
        }
    }
}

class NullPartProxyHandler implements InvocationHandler {

    private String nullName;

    private IPart proxied = new NPart();

    public NullPartProxyHandler(Class<? extends IPart> type) {
        nullName = type.getSimpleName() + ": [Null Part]";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }

    private class NPart implements Null, IPart {
        @Override
        public String toString() {
            return nullName;
        }
    }
}

interface IPart {
}

class Part3 implements IPart {

    private static Random rand = new Random(47);

    static List<Factory<? extends IPart>> partFactories = new ArrayList<>();

    static {
        partFactories.add(new FuelFilter3.Factory());
        partFactories.add(new AirFilter3.Factory());
        partFactories.add(new CabinAirFilter3.Factory());
        partFactories.add(new OilFilter3.Factory());
        partFactories.add(new FanBelt3.Factory());
        partFactories.add(new GeneratorBelt3.Factory());
        partFactories.add(new PowerSteeringBelt3.Factory());
    }

    public static IPart newNull(Class<? extends IPart> type) {
        return (IPart) Proxy.newProxyInstance(IPart.class.getClassLoader(),
                new Class<?>[]{Null.class, IPart.class}, new NullPartProxyHandler(type));
    }

    public static IPart createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Filter3 extends Part3 {
}

class FuelFilter3 extends Filter3 {
    // Create a Class Factory for each specific type:
    public static class Factory implements typeinfo.factory.Factory<FuelFilter3> {

        @Override
        public FuelFilter3 create() {
            return new FuelFilter3();
        }
    }
}

class AirFilter3 extends Filter3 {
    public static class Factory implements typeinfo.factory.Factory<AirFilter3> {

        @Override
        public AirFilter3 create() {
            return new AirFilter3();
        }
    }
}

class CabinAirFilter3 extends Filter3 {
    public static class Factory implements typeinfo.factory.Factory<CabinAirFilter3> {

        @Override
        public CabinAirFilter3 create() {
            return new CabinAirFilter3();
        }
    }
}

class OilFilter3 extends Filter3 {
    public static class Factory implements typeinfo.factory.Factory<OilFilter3> {

        @Override
        public OilFilter3 create() {
            return new OilFilter3();
        }
    }
}

class Belt3 extends Part3 {
}

class FanBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<FanBelt3> {

        @Override
        public FanBelt3 create() {
            return new FanBelt3();
        }
    }
}

class GeneratorBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<GeneratorBelt3> {

        @Override
        public GeneratorBelt3 create() {
            return new GeneratorBelt3();
        }
    }
}

class PowerSteeringBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<PowerSteeringBelt3> {

        @Override
        public PowerSteeringBelt3 create() {
            return new PowerSteeringBelt3();
        }
    }
}