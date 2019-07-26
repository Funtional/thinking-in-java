//: typeinfo/factory/Factory.java
package typeinfo.factory;

/**
 * 14.4 注册工厂
 *
 * @param <T>
 */
public interface Factory<T> {
    T create();
} ///:~