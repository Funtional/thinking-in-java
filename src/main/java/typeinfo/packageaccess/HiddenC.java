package typeinfo.packageaccess;

import typeinfo.interfacea.A;
import static net.mindview.util.Print.print;

/**
 * 实现包访问权限，这样包外部的客户端就不能看到它
 * @author Cheng Cheng
 * @date 2017-12-01 15:40
 */
public class HiddenC {
    public static A makeC(){
        return new C();
    }
}

class C implements A {

    @Override
    public void f() {
        print("public C.f()");
    }

    public void g(){
        print("public C.g()");
    }

    void u(){
        print("package C.u()");
    }

    protected void v(){
        print("protected C.v()");
    }

    private void w(){
        print("private C.w()");
    }
}