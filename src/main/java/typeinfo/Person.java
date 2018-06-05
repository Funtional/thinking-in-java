package typeinfo;

import net.mindview.util.Null;

/**
 * @author Cheng Cheng
 * @date 2017-11-30 16:27
 */
public class Person {
    public final String first;

    public final String last;

    public final String address;

    // etc
    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person: " + first + " " + last + " " + address;
    }

    public static class NullPerson extends Person implements Null {
        public NullPerson() {
            super("None", "None", "None");
        }

        @Override
        public String toString() {
            return "NullPerson";
        }
    }

    public static final Person NULL = new NullPerson();
}