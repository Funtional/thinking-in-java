package typeinfo.pets2;

import net.mindview.util.TypeCounter;
import typeinfo.factory.Factory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * 练习15：（4）使用注册工厂来实现一个新的PetCreator，并修改Pets外观，使其使用这个新的Creator
 *      而并非另外连个Creator。确保使用Pets.java的其他示例仍可以正常工作。
 * @author Cheng Cheng
 * @date 2017-11-28 11:45
 */
public class Pets {
    public static final PetCreator creator = new RFPetCreator();

    public static Pet randomPet() {
        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static List<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }


    static class RFPetCreator extends PetCreator {

        private static Random rand = new Random(47);

        static List<Factory<? extends Pet>> petFactories = Arrays.asList(
                new Mutt.Factory(), new Pug.Factory(), new EgyptianMau.Factory(), new Manx.Factory(),
                new Cymric.Factory(), new Rat.Factory(), new Mouse.Factory(), new Hamster.Factory());

        @Override
        public List<Class<? extends Pet>> types() {
            return null; // Dummy value, this method is not used!
        }

        @Override
        public Pet randomPet() {
            // Make 1 random Pet
            int n = rand.nextInt(petFactories.size());
            return petFactories.get(n).create();
        }
    }

    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(10)) {
            print(pet);
        }
    }
}