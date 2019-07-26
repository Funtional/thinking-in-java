package typeinfo;

/**
 * 14.2.3 新的转型语法
 *
 * @author Cheng Cheng
 * @date 2017-11-27 14:04
 */
public class ClassCast {
    public static void main(String[] args) {
        Building b = new House();
        House h = House.class.cast(b);
        System.out.println(h);
        h = (House) b; // ... or just do this
        System.out.println(h);
    }
}

class Building {
}

class House extends Building {
}