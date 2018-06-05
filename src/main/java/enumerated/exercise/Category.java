package enumerated.exercise;

import net.mindview.util.Generator;

import java.util.EnumMap;

import static enumerated.exercise.Input.*;

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),

    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),

    QUIT_TRANSACTION(ABORT_TRANSACTION),

    SHUT_DOWN(STOP);

    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input type : c.values) {
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}


// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {
    public Input next() {
        return Input.randomSelection();
    }
}