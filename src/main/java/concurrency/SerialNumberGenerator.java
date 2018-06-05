package concurrency;


/**
 * @author Cheng Cheng
 * @date 2018-04-25 17:26
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++; // Not thread-safe
    }
}
