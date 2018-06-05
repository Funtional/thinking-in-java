package containers.exercise;

import java.util.*;

import static net.mindview.util.Countries.*;
import static net.mindview.util.Print.*;

public class E01_CountryList {
    private static Random rnd = new Random(47);

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>(names(8));
        print(l);
        Collections.sort(l);
        print("sorted: " + l);
        for (int i = 1; i < 5; i++) {
            Collections.shuffle(l, rnd);
            print("shuffled (" + i + "): " + l);
        }

        List<String> c = new ArrayList<>(names(2));
        l.removeAll(c);

        SqList s1 = new SqList(Arrays.asList(1, 3, 5, 7, 9, 10));
        SqList s2 = new SqList(Arrays.asList(2, 3, 4, 5));
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s1 - s2 = " + s1.removeAll(s2));
        System.out.println("s1 = " + s1);
    }
}

class SqList {
    private Object[] elementData;
    private int size;

    public SqList() {
        this.elementData = new Object[10];
    }

    public SqList(Collection<Object> c) {
        this.elementData = c.toArray();
        this.size = elementData.length;
    }

    public boolean add(Object e) {
        elementData[size++] = e;
        return true;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elementData.length; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int size(){
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 移除所有出现在顺序表c中的元素
     * @param c
     * @return
     */
    public boolean removeAll(SqList c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 保留所有出现在顺序表c中的元素
     * @param c
     * @return
     */
    public boolean retainAll(SqList c){
        Objects.requireNonNull(c);
        return batchRemove(c,true);
    }

    /**
     * 批量删除操作
     * @param c
     * @param complement 是否删除补集
     * @return
     */
    private boolean batchRemove(SqList c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++) {
                if (c.contains(elementData[r]) == complement) {
                    elementData[w++] = elementData[r];
                }
            }
        } finally {
            if (r != size) {
                // 保持和AbstractCollection的兼容，即使c.contains()抛出异常
                System.arraycopy(elementData, r, elementData, w, size - r);
                w += size - r;
            }
            if (w != size) {
                for (int i = w; i < size; i++) {
                    // 通知GC回收
                    elementData[i] = null;
                }
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i != size - 1) {
                sb.append(',').append(' ');
            }
        }
        return sb.append(']').toString();
    }
}
