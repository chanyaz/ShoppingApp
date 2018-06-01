package com.tianyae.baselibrary.common

import android.app.Application
import android.content.Context
import com.tianyae.baselibrary.injection.component.AppComponent
import com.tianyae.baselibrary.injection.component.DaggerAppComponent
import com.tianyae.baselibrary.injection.module.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent


    companion object {
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        initAppInjection()

        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }



}