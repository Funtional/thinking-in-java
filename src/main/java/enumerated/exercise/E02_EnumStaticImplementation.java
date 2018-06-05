package enumerated.exercise;

import java.util.Random;

public class E02_EnumStaticImplementation {
    public static <T> void printNext() {
        System.out.print(CartoonCharacter.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance:
        for (int i = 0; i < 10; i++) {
            printNext();
        }
    }
}

enum CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private static Random rand = new Random(47);

    public static CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}