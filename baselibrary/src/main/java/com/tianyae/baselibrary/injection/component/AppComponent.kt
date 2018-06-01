package com.tianyae.baselibrary.injection.component

import android.content.Context
import com.tianyae.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun context():Context
}