package typeinfo;

import typeinfo.interfacea.A;
import typeinfo.packageaccess.HiddenC;

import java.lang.reflect.Method;

/**
 * @author Cheng Cheng
 * @date 2017-12-01 15:44
 */
public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeC();
        a.f();
        System.out.println(a.getClass().getName());
        // Compile error: cannot find symbol 'C':
        /*
        if(a instanceof C){
            C c=(C)a;
            c.g();
        }
        */
        // Oops! Reflection still allows us to call g():
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
}