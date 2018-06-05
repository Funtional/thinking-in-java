package strings.exercise;

import java.util.Locale;

/**
 * @author Cheng Cheng
 * @date 2017-11-15 16:01
 */
public class E06_ClassDump {

    public static void main(String[] args) {
        System.out.println(new DataHolder());
    }

}

class DataHolder {
    int intField = 1;
    long longField = 2L;
    float floatField = 3.0f;
    double doubleField = 4.0;

    @Override
    public String toString() {
        return new StringBuilder("DataHolder: \n")
                .append(format("intField: %d\n", intField))
                .append(format("longField: %d\n", longField))
                .append(format("floatField: %.2f\n", floatField))
                .append(format("doubleField: %.2e\n", doubleField))
                .toString();
    }

    public String format(String format, Object... args) {
        return String.format(Locale.CHINA, format, args);
    }
}