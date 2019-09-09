package com.simon.java.EffectiveJava;

/**
 * @author Simon
 * Function:创建和销毁对象
 */
public class Chapter2 {
    //第一条：使用静态工厂代替构造器
    //第二条：遇到多个构造器时要考虑使用构建器 可参考retrofit 构建 builder的设置返回builder本省 可以得到一个流式的api
    //第三条：用私有构造器或者枚举类型强化Singleton属性
    //第四条：通过私有构造器强化不可实例化的能力
    private Chapter2() {
        throw new IllegalStateException("Utility class");
    }
    //第五条：优先考虑依赖注入引入资源   重点 dagger Guice Spring  依赖注入会提升灵活性 与 可测试性  但会使大型项目凌乱不堪
    //第六条：避免创建不必要的对象  优先使用基本类型而不是装箱类型，要当心无意识的自动装箱
    String s = new String("test"); //don't do this

    //第七条：消除过期对象的引用  stack类自己管理内存
    // （1）只要类是自己管理内存，程序员都应该警惕内存泄漏问题  一旦对象被释放掉 该对象包含的任何引用应该被清空
    // （2）内存泄漏 另常见来源是缓存
    // （3）第三个常见来源是监听器和其他回调  注册回调没有进行取消 不采取行动 就会不断的积累起来  最佳做法只保存弱引用

    //第八条 避免使用终结方法和清除方法  终结方法 finalizer 通常是不可预测的 也是很危险的 java中用cleaner 代替了终结方法 没那么危险
    //但仍然是不可预测的 运行缓慢 一般情况下也是不需要的

    //第九条 try-with-resources 优先于 try-finally
}

