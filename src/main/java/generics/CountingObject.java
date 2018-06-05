package generics;

public class CountingObject {
    private static long counter = 0;
    private long id = counter++;

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "CountingObject " + id;
    }
}
