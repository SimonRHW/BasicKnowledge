package com.simon.kotlin.dagger

import dagger.Module
import dagger.Provides

@Module
class TestTwoModule {

    @Provides
    fun provideApiService(): ApiTwoServices = ApiTwoServices()

}