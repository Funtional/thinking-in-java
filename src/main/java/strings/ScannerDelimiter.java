package strings;

import java.util.Scanner;

/**
 * 13.7.1 Scanner定界符
 * @author Cheng Cheng
 * @date 2017-11-22 16:56
 */
public class ScannerDelimiter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
        //返回当前正在作为定界符使用的Pattern对象
        System.out.println(scanner.delimiter());
    }
}