package innerclasses;

import java.util.*;

/**
 * @author Cheng Cheng
 * @date 2017-10-19 10:29
 */
public class AsListInterface {
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());
        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());

        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3, new Light(), new Heavy());
    }
}

class Snow{}
class Powder extends Snow{}
class Light extends Powder{}
class Heavy extends Powder{}

class Crusty extends Snow{}
class Slush extends Snow{}