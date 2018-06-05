package containers.exercise;

import net.mindview.util.Countries;

import java.util.*;

class SimpleHashSet<K> extends AbstractSet<K> {
    static final int SIZE = 997;
    @SuppressWarnings("unchecked")
    private LinkedList<K>[] buckets = new LinkedList[SIZE];

    @Override
    public boolean add(K key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<K>();
        }
        LinkedList<K> bucket = buckets[index];
        ListIterator<K> it = bucket.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(key)) {
                return false;
            }
        }
        // Sets do not permit duplicates and one
        // was already in the set.
        it.add(key);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return false;
        }
        ListIterator<K> it = buckets[index].listIterator();
        while (it.hasNext()) {
            if (it.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        int sz = 0;
        for (LinkedList<K> bucket : buckets) {
            if (bucket != null) {
                sz += bucket.size();
            }
        }
        return sz;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int count;
            private boolean canRemove;
            private int index1, index2;

            @Override
            public boolean hasNext() {
                return count < size();
            }

            @Override
            public K next() {
                if (hasNext()) {
                    canRemove = true;
                    ++count;
                    for (; ; ) {
                        // Position of the next non-empty bucket
                        while (buckets[index1] == null) {
                            index1++;
                        }
                        // Position of the next item to return
                        try {
                            return buckets[index1].get(index2++);
                        } catch (IndexOutOfBoundsException e) {
                            ++index1;
                            index2 = 0;
                        }
                    }
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if (canRemove) {
                    canRemove = false;
                    buckets[index1].remove(--index2);
                    if (buckets[index1].isEmpty()) {
                        buckets[index1] = null;
                    }
                    --count;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }
}

public class E24_SimpleHashSet {
    public static void main(String[] args) {
        SimpleHashSet<String> m = new SimpleHashSet<String>();
        m.addAll(Countries.names(10));
        m.addAll(Countries.names(10));
        System.out.println("m = " + m);
        System.out.println("m.size() = " + m.size());
        Iterator<String> it = m.iterator();
        System.out.println("it.next()= " + it.next());
        it.remove();
        System.out.println("it.next()= " + it.next());
        System.out.println("m = " + m);
        m.remove("ALGERIA");
        System.out.println("m = " + m);
    }
}
