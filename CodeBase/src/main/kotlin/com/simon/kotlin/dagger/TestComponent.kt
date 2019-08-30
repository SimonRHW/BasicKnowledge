package com.simon.kotlin.dagger

import dagger.Component


@Component(modules = [TestModule::class, TestTwoModule::class])
interface TestComponent {
    fun inject(test: DaggerTest)
}