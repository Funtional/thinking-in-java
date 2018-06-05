package typeinfo.exercise;

import java.lang.reflect.Constructor;

/**
 * @author Cheng Cheng
 * @date 2017-11-29 15:38
 */
public class E19_ReflectionToyCreation {
    public static Toy makeToy(String toyName, int IQ) {
        try {
            Class<?> tClass = Class.forName(toyName);
            for (Constructor<?> ctor : tClass.getConstructors()) {
                // Look for a constructor with a single parameter:
                Class<?>[] params = ctor.getParameterTypes();
                if (params.length == 1 && params[0] == int.class) {
                    return (Toy) ctor.newInstance(new Object[]{IQ});
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(makeToy("typeinfo.exercise.SuperToy", 102));
    }
}

class SuperToy extends FancyToy {
    int IQ;

    public SuperToy(int intelligence) {
        IQ = intelligence;
    }

    @Override
    public String toString() {
        return "I'm a SuperToy. I'm smarter than you.";
    }
}