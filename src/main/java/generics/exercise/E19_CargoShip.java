package generics.exercise;

import generics.Generators;
import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * 商品
 */
class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ": " + description + ", price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);

        @Override
        public Product next() {
            return new Product(rand.nextInt(1000), "Test", Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };
}

/**
 * 集装箱
 */
class Container extends ArrayList<Product> {
    public Container(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

/**
 * 货仓
 */
class CargoHold extends ArrayList<Container> {
    public CargoHold(int nContainers, int nProducts) {
        for (int i = 0; i < nContainers; i++) {
            add(new Container(nProducts));
        }
    }
}

/**
 * 吊车
 */
class Crane {
}

/**
 * 指挥部
 */
class CommandSection {
}

/**
 * 货船
 */
class CargoShip extends ArrayList<CargoHold> {
    private ArrayList<Crane> cranes = new ArrayList<>();
    private CommandSection cmdSection = new CommandSection();

    public CargoShip(int nCargoHolds, int nContainers, int nProducts) {
        for (int i = 0; i < nCargoHolds; i++) {
            add(new CargoHold(nContainers, nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (CargoHold ch : this) {
            for (Container c : ch) {
                for (Product p : c) {
                    result.append(p).append("\n");
                }
            }
        }
        return result.toString();
    }
}

public class E19_CargoShip {
    public static void main(String[] args) {
        System.out.println(new CargoShip(14, 5, 10));
    }
}
