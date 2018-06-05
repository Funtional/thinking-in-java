package strings.exercise;

import java.util.Locale;
import java.util.Scanner;

/**
 * 练习20: （2）编写一个包含int、long、float、double和String属性的类。为它编写一个构造器，接收一个String参数。
 *   然后扫描该字符串，为各个属性赋值。再添加一个toString()方法，用来掩饰你的类是否工作正确。
 * @author Cheng Cheng
 * @date 2017-11-22 16:50
 */
public class E20_Scanner {

    public static void main(String[] args) {
        DataHolder2 dh = new DataHolder2("1 10000000000000 1.1 1e55 Howdy Hi");
        System.out.println(dh.toString());
    }
}

class DataHolder2{
    private int i;
    private long l;
    private float f;
    private double d;
    private String s;

    DataHolder2(String data) {
        Scanner stdin = new Scanner(data);
        stdin.useLocale(Locale.US);
        i = stdin.nextInt();
        l = stdin.nextLong();
        f = stdin.nextFloat();
        d = stdin.nextDouble();
        s = stdin.next("\\w+");
    }
    public String toString() {
        return i + " " + l + " " + f + " " + d + " " + s;
    }
}