package com.tianyae.baselibrary.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {
    @Provides
    fun providerLifecycleProvider(): LifecycleProvider<*> {
        return this.lifecycleProvider
    }
}
