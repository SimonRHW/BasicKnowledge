package com.simon.java.design.pattern;

/**
 * 单例模式知识点：类加载、并发、序列化
 * <p>
 * 单例模式 保证一个类只有一个实例，并且提供一个全局的可以访问的入口。
 * <p>
 * 作用：
 * 一、节省内存，节省计算，很多情况下，我们只需要一个实例就行了，如果出现多个实例纯属于浪费；
 * 举个例子拿一个初始化比较耗时的来说，在这个类构造的时候，需要查询数据库并对查到的数据进行大量的计算。
 * 所以在第一次构造的时候，我们花了很多时间来初始化找个对象。假设我们数据库的数据是不变的，并且把这些对象保存在内存中，
 * 那么以后就可以用同一个实例了，如果每次都重新生成新的实例，实在是没有必要；
 * 二、为了保证结果的准确性，比如我们提供一个全局的计算器，用来统计人数，如果多个实例反而会造成混乱
 * 三、方便管理
 * <p>
 * 主要有以下方式：
 * 饿汉式、懒汉式、双重检查式、静态内部类式、枚举式
 * <p>
 * Effective Java 明确表示 使用枚举实现单例的方法虽然没有被广泛采用，但是单元素的枚举类型已经成为了实现单例模式的最佳方法。
 * <p>
 * 安全问题不可小视，一旦生成多个实例，单例模式就彻底没用了
 */
public class SingletonPattern {

    public static class Singleton {

        private static volatile Singleton singleton;

        private Singleton() {
        }

        /*
        懒汉式写法，在getSingleton调用时才会去实例化我们的对象，起到懒加载效果，但是只能在单线程中使用。

        当一个线程计入到 if (singleton != null) 判断语句时，还没来的及往下执行，另一个线程也通过了这个判断语句，这时就会多次创建实例
         */
        private static Singleton getInstance() {
            if (singleton != null) {
                singleton = new Singleton();
            }
            return singleton;
        }

        /*
        加上 synchronized关键字  解决了线程安全问题
        效率会比较低，每个线程想要获取到实例的时候，执行getInstanceSynchronized 都需要进行同步。
        多个线程不能同时访问，然而这在大多数情况下是没有必要的。
         */
        private static synchronized Singleton getInstanceSynchronized() {
            if (singleton != null) {
                singleton = new Singleton();
            }
            return singleton;
        }

        /*
          为了提供效率缩小同步范围 synchronized 从方法上移除；将synchronized放到方法内部，采用代码块的形式来保护线程安全
          有问题：假如第一个线程通过 if (singleton == null) 判断语句还没来的及往下执行，另一个线程也通过了这个判断语句，此时也会产生多个实例
         */
        private static Singleton getInstanceCodeBlock() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    singleton = new Singleton();
                }
            }
            return singleton;
        }

        /*
        为了解决以上问题，我们通常使用双重检查模式，进行两次 (singleton == null)就可以保证线程安全了
        效率安全、延时加载、效率也更高

        private static volatile Singleton singleton;
        为什么要加 volatile 关键字？
        主要在于singleton = new Singleton()这一句。
        这并不是一个原子操作，会存在以下步骤：
        1、会给singleton分配内存空间
        2、调用singleton的构造函数等来初始化singleton
        3、将singleton 对象指向分配的内存空间（执行完着一步，singleton 就不是null了）
        存在重排序的优化，第二部和第三步的顺序是不能保证的。
        如果是 1-2-3 没有问题，
        如果是 1-3-2 的情况下，在第三步执行后singleton并不是null 但是此时第二步是没有执行的，假设此时另一个线程进入了getInstanceDCL方法
        由于此时的singleton已经不是null了，所以他会通过第一层的检查并且直接返回，但实际上这个时候的singleton并没有完全的完成初始化，这样使用该实例会报错。

        使用volatile可以防止上述所说的重排序，就可以避免拿到未完成初始化的对象
         */
        private static Singleton getInstanceDCL() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }

        /*
        静态内部类写法，他跟饿汉式的方法采用的机制类似。都采用的类装载的机制，来保证我们初始化实例时只有一个线程，
        在这里JVM帮助我们保证了线程的安全性  优点与双重检查模式是一样的，但是都不能防止被反序列化成多个实例
         */
        private static class SingletonInstance {
            private static final Singleton singleton = new Singleton();
        }

        private static Singleton getInstanceStaticInnerClass() {
            return SingletonInstance.singleton;
        }

        /*
        枚举类写法:这不仅能够避免多线程同步的问题，而且还能够防止反序列化和反射创建新的对象
        枚举写法是懒加载的，在首次访问它之前，不会初始化其值，
        具体展开可以参考https://stackoverflow.com/questions/16771373/singleton-via-enum-way-is-lazy-initialized
        优点：
        写法简洁：需要我们自己去考虑懒加载、线程安全等问题
        线程安全： java类的加载由JVM保证线程安全
        防止破坏单例：
        （1）JAVA专门对枚举的序列化做了规定，仅仅式将枚举对象的name属性输出到结果中，反序列化时通过java.lang.Enum的valueOf方法来根据名字查找对象，
        而不是创建一个新的对象， 这就防止的反序列化导致的单例破坏问题的出翔。
        （2）对于通过反射破坏单例对象时，枚举同样有防御措施。反射通过newInstance创建对象时，会检查这个类是否时枚举类，
        如果是的化会抛出参数异常cannot reflectively create enum objects
         */
        public enum SingletonEnum {
            INSTANCE;
            public void doSomething() {

            }
        }

    }

}
