package typeinfo;

import net.mindview.util.TypeCounter;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import static net.mindview.util.Print.*;

/**
 * 14.3.3 递归计数
 * @author Cheng Cheng
 * @date 2017-11-28 14:38
 */
public class PetCount4 {
    // Class.isAssignableFrom()执行运行时的检查，以校验你传递的对象确实属于我们感兴趣的继承结构
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            printnb(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        print();
        print(counter);
    }
}