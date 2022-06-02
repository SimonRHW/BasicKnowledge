package com.simon.kotlin.design.pattern

// Kotlin 当中 5 种单例模式的写法。除了最后一种“接口模板”的方式，是为了学习研究不被推荐使用以外，
// 其他 4 种单例模式都是有一定使用场景的。这 4 种单例之间各有优劣，我们可以在工作中根据实际需求，来选择对应的实现方式：
// 如果我们的单例占用内存很小，并且对内存不敏感，不需要传参，直接使用 object 定义的单例即可。
// 如果我们的单例占用内存很小，不需要传参，但它内部的属性会触发消耗资源的网络请求和数据库查询，我们可以使用 object 搭配 by lazy 懒加载。
// 如果我们的工程很简单，只有一两个单例场景，同时我们有懒加载需求，并且 getInstance() 需要传参，我们可以直接手写 Double Check。
// 如果我们的工程规模大，对内存敏感，单例场景比较多，那我们就很有必要使用抽象类模板 BaseSingleton 了。

object Manager {
    fun foo() {}
}

//所谓的工厂模式，就是指当我们想要统一管理一个类的创建时，
// 我们可以将这个类的构造函数声明成 private，然后用工厂模式来暴露一个统一的方法，以供外部使用。
// Kotlin 的伴生对象非常符合这样的使用场景：
//  私有的构造函数，外部无法调用
//            ↓
class Singleton private constructor(name: String) {
    companion object {
        @JvmStatic
        fun create(name: String): Singleton {
            // 统一检查，比如过滤
            return Singleton(name)
        }
    }
}

object SingletonManager {
    // 对外暴露的 user
    val singleton by lazy { singleton() }

    private fun singleton(): Singleton {
        return Singleton.create("tom")
    }
}

class SingletonPattern private constructor(name: String) {
    companion object {
        @Volatile
        private var INSTANCE: SingletonPattern? = null

        fun getInstance(name: String): SingletonPattern =
            // 第一次判空
            INSTANCE ?: synchronized(this) {
                // 第二次判空
                INSTANCE ?: SingletonPattern(name).also { INSTANCE = it }
            }
    }
}

// 注释①：abstract 关键字，代表了我们定义的 BaseSingleton 是一个抽象类。我们以后要实现单例类，就只需要继承这个 BaseSingleton 即可。
// 注释②：in P, out T 是 Kotlin 当中的泛型，P 和 T 分别代表了 getInstance() 的参数类型和返回值类型。注意，这里的 P 和 T，是在具体的单例子类当中才需要去实现的。如果你完全不知道泛型是什么东西，可以先看看泛型的介绍，我们在第 10 讲会详细介绍 Kotlin 泛型。
// 注释③：creator(param: P): T 是 instance 构造器，它是一个抽象方法，需要我们在具体的单例子类当中实现此方法。
// 注释④：creator(param) 是对 instance 构造器的调用。
//  ①                          ②
//  ↓                           ↓
abstract class BaseSingleton<in P, out T> {
    @Volatile
    private var instance: T? = null

    //                       ③
    //                       ↓
    protected abstract fun creator(param: P): T

    fun getInstance(param: P): T =
        instance ?: synchronized(this) {
            //            ④
            //            ↓
            instance ?: creator(param).also { instance = it }
        }
}


interface ISingleton<P, T> {

    var instance: T?

    fun creator(param: P): T

    fun getInstance(p: P): T =
        instance ?: synchronized(this) {
            instance ?: creator(p).also { instance = it }
        }
}

fun main() {
    // 使用
    SingletonPattern.getInstance("Tom")
}
