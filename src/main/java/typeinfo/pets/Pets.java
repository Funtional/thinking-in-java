package typeinfo.pets;

import java.util.List;

/**
 * @author Cheng Cheng
 * @date 2017-11-28 11:45
 */
public class Pets {
    public static final PetCreator creator = new LiteralPetCreator();

    public static Pet randomPet() {
        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static List<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }
}