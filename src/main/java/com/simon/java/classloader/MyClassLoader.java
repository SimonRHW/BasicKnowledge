package com.simon.java.classloader;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Simon
 * @date 2021/2/28 16:23
 * Desc ：在 Java 程序启动的时候，并不会一次性加载程序中所有的 .class 文件，而是在程序的运行过程中，动态地加载相应的类到内存中。
 * 通常情况下,Java程序中的.class文件会在以下2种情况下被 ClassLoader主动加载到内存中：
 * 1、调用类构造器
 * 2、调用类中的静态（static）变量或者静态方法
 *
 * JVM 中自带 3 个类加载器：
 * 启动类加载器 BootstrapClassLoader （非Java实现，属于虚拟机的一部分，无法在 Java 代码中直接获取它的引用）
 * 扩展类加载器 ExtClassLoader （JDK 1.9 之后，改名为 PlatformClassLoader）
 * 系统加载器 APPClassLoader
 *
 * 加载模式：
 * 双亲委派模式
 * 所谓双亲委派模式就是，当类加载器收到加载类或资源的请求时，通常都是先委托给父类加载器加载，
 * 也就是说，只有当父类加载器找不到指定类或资源时，自身才会执行实际的类加载过程。
 *
 * 默认情况下，JVM 首先使用 AppClassLoader 去加载 Test 类。
 *
 * AppClassLoader 将加载的任务委派给它的父类加载器（parent）—ExtClassLoader。
 *
 * ExtClassLoader 的 parent 为 null，所以直接将加载任务委派给 BootstrapClassLoader。
 *
 * BootstrapClassLoader 在 jdk/lib 目录下无法找到 Test 类，因此返回的 Class 为 null。
 *
 * 因为 parent 和 BootstrapClassLoader 都没有成功加载class，所以AppClassLoader会调用自身的 findClass方法来加载class。
 */
public class MyClassLoader extends java.lang.ClassLoader {

    private String filePath;

    public MyClassLoader(String path) {
        filePath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String newPath = filePath+name+".class";
        byte[] classBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(newPath));
            classBytes = Files.readAllBytes(path);
        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
        return defineClass(name,classBytes,0,classBytes.length);
    }


    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("G:\\IdeaProjects\\BasicKnowledge\\src\\main\\java\\com\\simon\\java\\classloader\\");
        try {
            Class<?> aClass = myClassLoader.loadClass("com.simon.java.classloader.ClassLoaderTest");
            if(aClass != null){
                //java 9 该方法已过时
                Object instance = aClass.newInstance();
                Method printTest = aClass.getDeclaredMethod("printTest");
                printTest.invoke(instance);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


//源码部分：
//    protected Class<?> loadClass(String name, boolean resolve)
//            throws ClassNotFoundException
//    {
//        synchronized (getClassLoadingLock(name)) {
//            // First, check if the class has already been loaded
//            判断该 Class 是否已加载，如果已加载，则直接将该 Class 返回。
//            Class<?> c = findLoadedClass(name);
//            if (c == null) {
//                long t0 = System.nanoTime();
//                try {
//                    如果该 Class 没有被加载过，则判断 parent 是否为空，如果不为空则将加载的任务委托给parent。
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
//                    如果 parent == null，则直接调用 BootstrapClassLoader 加载该类。
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }
//
//                if (c == null) {
//                    // If still not found, then invoke findClass in order
//                    // to find the class.
//                    如果 parent 或者 BootstrapClassLoader 都没有加载成功，则调用当前 ClassLoader 的 findClass 方法继续尝试加载。
//                    long t1 = System.nanoTime();
//                    c = findClass(name);
//
//                    // this is the defining class loader; record the stats
//                    PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                    PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    PerfCounter.getFindClasses().increment();
//                }
//            }
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//        }
//    }