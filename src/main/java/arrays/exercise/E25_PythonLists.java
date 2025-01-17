package arrays.exercise;

import java.util.*;

import static net.mindview.util.Print.print;

public class E25_PythonLists {
    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        print(aList.getClass().getSimpleName());
        print(aList);
        print(aList.get(4));
        aList.add(6);
        aList.addAll(Arrays.asList(7, 8));
        print(aList);
        List<Integer> aSlice = aList.subList(2, 4);
        print(aSlice);

        class MyArrayList<T> extends ArrayList<T> {
            MyArrayList(Collection<? extends T> c) {
                super(c);
            }

            MyArrayList(int initialCapacity) {
                super(initialCapacity);
            }

            List<T> getReserved() {
                List<T> reversed = new MyArrayList<>(size());
                ListIterator<T> it = listIterator(size());
                while (it.hasPrevious()) {
                    reversed.add(it.previous());
                }
                return reversed;
            }
        }

        MyArrayList<Integer> list2 = new MyArrayList<Integer>(aList);
        print(list2.getClass().getSimpleName());
        print(list2.getReserved());
    }
}