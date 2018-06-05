package arrays.exercise;

/**
 * @author Cheng Cheng
 * @date 2017-12-05 16:59
 */
public class ContainerComparison {

}

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }
}