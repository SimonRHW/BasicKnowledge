package com.simon.kotlin.dagger

import dagger.Module
import dagger.Provides

@Module
class TestModule {

    @Provides
    fun provideApiService(): ApiServices = ApiServices()

    @Provides
    fun provideApiThreeService(): ApiThreeServices = ApiThreeServices()

}