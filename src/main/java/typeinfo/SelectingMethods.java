package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.mindview.util.Print.print;

/**
 * @author Cheng Cheng
 * @date 2017-11-29 19:04
 */
public class SelectingMethods {
    /**
     * OutPut:
     * boring1
     * boring2
     * Proxy detected the interesting method
     * interesting bonobo
     * boring3
     */
    public static void main(String[] args) {
        /**调用静态方法Proxy.newProxyInstance可以创建动态代理*/
        /**参数列表：类加载器，该代理实现的接口列表（不是类或抽象类），以及InvocationHandler接口的一个实现**/
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(SomeMethods.class.getClassLoader(), new Class[]{SomeMethods.class}, new MethodSelector(new Implementation()));
        proxy.boring1(); //应该把这些调用想象成是 MethodSelector.invoke()
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
}

/**
 * 需要进行的而外操作，在此类定义，需要继承InvocationHandler（调用处理器）
 */
class MethodSelector implements InvocationHandler {
    private Object proxied;

    /**通常调用处理器的构造器需要一个“实际”对象的引用，使得调用处理器在执行中介任务时，可以将请求转发**/
    public MethodSelector(Object proxied) { //此处的参数是否可以改为接口SomeMethods？
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting")) {
            print("Proxy detected the interesting method");
        }
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {
    void boring1();

    void boring2();

    void interesting(String args);

    void boring3();
}

class Implementation implements SomeMethods {

    @Override
    public void boring1() {
        print("boring1");
    }

    @Override
    public void boring2() {
        print("boring2");
    }

    @Override
    public void interesting(String args) {
        print("interesting " + args);
    }

    @Override
    public void boring3() {
        print("boring3");
    }
}