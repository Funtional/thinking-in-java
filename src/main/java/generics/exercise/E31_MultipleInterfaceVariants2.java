package generics.exercise;

interface Payable<T> {
}


class Employee1 implements Payable {
}

class Hourly extends Employee1 implements Payable {
}

public class E31_MultipleInterfaceVariants2 {
    public static void main(String[] args) {
        new Employee1();
        new Hourly();
    }
}