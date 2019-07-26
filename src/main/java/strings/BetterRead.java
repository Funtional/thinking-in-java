package strings;

import java.util.Scanner;

/**
 * 13.7 扫描输入
 *
 * @author Cheng Cheng
 * @date 2017-11-22 16:34
 */
public class BetterRead {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(SimpleRead.input);
        System.out.println("What is your name?");
        String name = stdin.nextLine();
        System.out.println(name);

        System.out.println("How old are your? What is your favorite double?");
        System.out.println("(input: <age> <double>)");
        int age = stdin.nextInt();
        double favorite = stdin.nextDouble();

        System.out.println(age);
        System.out.println(favorite);
        System.out.printf("Hi %s.\n", name);
        System.out.printf("In 5 years you will be %d.\n", age + 5);
        System.out.printf("My favorite double is %f.", favorite / 2);
    }

}