package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cheng Cheng
 * @date 2018-04-19 10:49
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
       try{
           ExecutorService exec = Executors.newCachedThreadPool();
           exec.execute(new ExceptionThread());
       } catch (Exception e){
           System.out.println(e);
       }
        System.out.println("end");
    }
}
