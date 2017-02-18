package cn.galudisu.fp._2_1_singletons;

import org.apache.commons.lang3.Validate;

/**
 * 单例模式(Singleton)：单例模式表示仅能存在一个对象，我们如何保证它创建只有一个对象？
 * 方法是令构造器不可访问，这样就不能通过new 关键字创建新的对象。
 *
 * @author galudisu
 */
public class Singleton {

    // Eager initialization
    private static final Singleton instance = new Singleton();  // 静态的、final的

    private Singleton() { // 不能访问构造器

    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        Validate.isTrue(singleton == singleton2);
    }

}

// 单例模式有很多形式，有null检测版本和双重锁模式版本。
