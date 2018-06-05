package arrays;

/**
 * @author Cheng Cheng
 * @date 2017-12-07 10:32
 */
public class ArrayOfGenericType<T> {
    T[] array; // OK

    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        // ! array = new T[size]; // Illegal
        array = (T[]) new Object[size]; // "unchecked" Warning
    }

    // Illegal:
    /*
    !
    public <U> U U[] makeArray(){
        return new U[10];
    }
    */
}