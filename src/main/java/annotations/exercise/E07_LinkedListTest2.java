package annotations.exercise;

import net.mindview.atunit.Test;

import java.util.LinkedList;

public class E07_LinkedListTest2 extends LinkedList {
    @Test
    void initialization() {
        assert isEmpty();
    }

    @Test
    void _contains() {
        add("one");
        assert contains("one");
    }

    @Test
    void _remove() {
        add("one");
        remove("one");
        assert isEmpty();
    }

}
