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
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> { // Class -> Integer
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0)); // 把所有类型都预先设置好了，数量为0的也可以展示出来
        }

        public void count(Pet pet) {
            // Class.isInstance() eliminates instanceofs:
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) { // Class -> Integer
                // Class.isInstance动态的测试对象，instanceof 是与命名类型比较
                //如果要添加西新类型的Pet，只需要修改LiteralPetCreator，程序其他地方不用改变（如果使用instanceof不可能做到）。
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