package containers.exercise;

import net.mindview.util.Countries;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SlowSet<K> extends AbstractSet<K> {
    private List<K> keys = new ArrayList<K>();

    @Override
    public boolean add(K k) {
        if (!keys.contains(k)) {
            keys.add(k);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    @Override
    public int size() {
        return keys.size();
    }
}

public class E18_SlowSet {
    public static void main(String[] args) {
        SlowSet<String> set = new SlowSet<String>();
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10));
        System.out.println(set);
    }
}
