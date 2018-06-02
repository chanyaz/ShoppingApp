package com.tianyae.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.tianyae.baselibrary.injection.ActivityScope
import com.tianyae.baselibrary.injection.module.ActivityModule
import com.tianyae.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [(ActivityModule::class), (LifecycleProviderModule::class)])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}