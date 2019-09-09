package com.simon.kotlin.dagger

fun main() {
    val daggerTest = DaggerTest()
    //DaggerTestComponent 实现TestComponent 接口 ，实例化相应Module ，并通过inject 建立 MembersInjector 与 ProvideFactory与需要注入对象的关联
    //TestModule_ProvideApiServiceFactory  通过静态工厂类实现  调用相应Module 创建依赖对象
    //DaggerTest_MembersInjector  通过 inject 将 Module 拿到的依赖  注入到需要依赖的对象上
    DaggerTestComponent.create().inject(daggerTest)
    daggerTest.doSomeThing()
}

