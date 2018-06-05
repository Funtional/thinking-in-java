package annotations.exercise;

import net.mindview.atunit.Test;

import java.util.HashSet;

public class E04_TestSetupFixture {
    HashSet<String> testObject = new HashSet<>();

    @Test
    void t1() {
        assert testObject.isEmpty();
        testObject.add("one");
    }

    @Test
    void t2() {
        assert testObject.isEmpty();
        testObject.add("one");
    }
}
