package containers.exercise;

import net.mindview.util.Countries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

public class E07_CrossInsertion {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>(Countries.names(10));
        LinkedList<String> ll = new LinkedList<String>(Countries.names(20).subList(10, 20));
        for (Iterator<String> it = al.iterator(); it.hasNext(); ) {
            print(it.next());
        }
        print("********");
        for (Iterator<String> it = ll.iterator(); it.hasNext(); ) {
            print(it.next());
        }
        print("********");

        int alIndex = 0;
        for (ListIterator<String> lit = ll.listIterator(); lit.hasNext(); ) {
            al.add(alIndex, lit.next());
            alIndex += 2;
        }
        print("al = " + al);
        print("********");

        alIndex = 0;
        // Start at the end:
        for (ListIterator<String> lit = ll.listIterator(ll.size()); lit.hasPrevious(); ) {
            al.add(alIndex, lit.previous());
            alIndex += 2;
        }
        print("al = " + al);
    }
}
