package com.tianyae.baselibrary.injection.module

import android.content.Context
import com.tianyae.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providersContext(): Context {
        return context
    }
}