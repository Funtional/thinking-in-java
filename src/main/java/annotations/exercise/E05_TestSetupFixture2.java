package annotations.exercise;

import net.mindview.atunit.Test;

import java.util.HashSet;

public class E05_TestSetupFixture2 extends HashSet {

    @Test
    void t1() {
        assert isEmpty();
        add("one");
    }

    @Test
    void t2() {
        assert isEmpty();
        add("one");
    }
}
