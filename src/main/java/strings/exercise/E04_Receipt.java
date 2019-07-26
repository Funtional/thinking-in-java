package strings.exercise;

import java.util.Formatter;

/**
 * @author Cheng Cheng
 * @date 2017-11-15 14:08
 */
public class E04_Receipt {

    private static final int ITEM_WIDTH = 15;
    private static final int QTY_WIDTH = 5;
    private static final int PRICE_WIDTH = 10;
    private static final String TITLE_FMT = "%-" + ITEM_WIDTH + "s %" + QTY_WIDTH + "s %" + PRICE_WIDTH + "s\n";
    private static final String ITEM_FMT = "%-" + ITEM_WIDTH + "." + ITEM_WIDTH + "s %" + QTY_WIDTH + "d %" + PRICE_WIDTH + ".2f\n";
    private static final String TOTAL_FMT = "%-" + ITEM_WIDTH + "s %" + QTY_WIDTH + "s %" + PRICE_WIDTH + ".2f\n";

    private double total = 0;

    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        f.format(TITLE_FMT, "Item", "Qty", "Price");
        f.format(TITLE_FMT, "----", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        f.format(ITEM_FMT, name, qty, price);
        total += price;
    }

    public void printTotal() {
        f.format(TOTAL_FMT, "Tax", "", total * 0.06);
        f.format(TITLE_FMT, "", "", "-----");
        f.format(TOTAL_FMT, "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        E04_Receipt e04Receipt = new E04_Receipt();
        e04Receipt.printTitle();
        e04Receipt.print("Jack's Magic Beans", 4, 4.25);
        e04Receipt.print("Princess Peas", 3, 5.1);
        e04Receipt.print("Three Bears Porridge", 1, 14.29);
        e04Receipt.printTotal();
    }
}