package typeinfo;

import net.mindview.util.MapData;
import typeinfo.pets.LiteralPetCreator;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.mindview.util.Print.*;

/**
 * 14.3.2 动态的instanceof
 *
 * @author Cheng Cheng
 * @date 2017-11-28 11:49
 */
public class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }

        public void count(Pet pet) {
            // Class.isInstance() eliminates instanceofs:
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(pet)) {
                    put(pair.getKey(), pair.getValue() + 1);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                builder.append(pair.getKey().getSimpleName())
                        .append("=")
                        .append(pair.getValue())
                        .append(",  ");
            }
            builder.delete(builder.length() - 2, builder.length()).append("}");
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter counter = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            printnb(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        print();
        print(counter);
    }
}