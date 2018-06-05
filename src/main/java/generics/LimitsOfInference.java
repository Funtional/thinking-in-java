package generics;

import net.mindview.util.New;
import typeinfo.Person;
import typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class LimitsOfInference {

    static void f(Map<Person, List<? extends Pet>> petPeople) {
        System.out.println(petPeople);
    }

    public static void main(String[] args) {
        f(New.map()); // Java8之前编译出错，原因是：New.map()的返回是Map<Object, Object>
    }
}
