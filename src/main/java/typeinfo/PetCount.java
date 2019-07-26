package typeinfo;

import typeinfo.pets.*;

import java.util.HashMap;

/**
 * @author Cheng Cheng
 * @date 2017-11-27 16:10
 */
public class PetCount {

    /**
     * 一个Map计数器，用于计算每个类型有多少个对象； 类型--> 数量
     */
    static class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }
        }
    }

    /**
     * 传入PetCreator，可以计算该PetCreator创建的数组中，各个宠物的数量
     *
     * @param creator
     */
    public static void countPets(PetCreator creator) {
        PetCounter counter = new PetCounter();
        int size = 20;
        for (Pet pet : creator.createArray(size)) {
            // List each individual pet:
            System.out.print(pet.getClass().getSimpleName() + " ");
            if (pet instanceof Pet) {
                counter.count("Pet");
            }
            if (pet instanceof Dog) {
                counter.count("Dog");
            }
            if (pet instanceof Mutt) {
                counter.count("Mutt");
            }
            if (pet instanceof Pug) {
                counter.count("Pug");
            }
            if (pet instanceof Cat) {
                counter.count("Cat");
            }
            if (pet instanceof Manx) {
                counter.count("Manx");
            }
            if (pet instanceof EgyptianMau) {
                counter.count("EgyptianMau");
            }
            if (pet instanceof Cymric) {
                counter.count("Cymric");
            }
            if (pet instanceof Rodent) {
                counter.count("Rodent");
            }
            if (pet instanceof Rat) {
                counter.count("Rat");
            }
            if (pet instanceof Mouse) {
                counter.count("Mouse");
            }
            if (pet instanceof Hamster) {
                counter.count("Hamster");
            }
            if (pet instanceof Gerbil) {
                counter.count("Gerbil");
            }
        }
        System.out.println();
        System.out.println(counter);
    }

    public static void main(String[] args) {
        countPets(new ForNameCreator());
    }
}