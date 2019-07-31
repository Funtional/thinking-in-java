package net.mindview.util;

import java.util.HashSet;
import java.util.Set;

public class Sets {
    /**
     * 求并集
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) { //协会;联合会;会社;俱乐部;同盟;联盟;联邦;(尤指内战时期的)美利坚合众国，美国
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 求交集
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) { //十字路口;交叉路口;交点;横断;交叉;相交
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    // Subtract subset from superset:
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) { //差别;差异;不同(之处);变化(之处);差;差额;意见分歧;不和
        Set<T> result = new HashSet<T>(superset);
        result.removeAll(subset);
        return result;
    }

    /**
     * 求补集
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    // Reflexive--everything not in the intersection:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {//补充物;补足物;足数;足额;补足语;补语
        return difference(union(a, b), intersection(a, b));
    }
}
