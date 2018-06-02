package com.tianyae.baselibrary.injection.module

import android.app.Activity
import com.tianyae.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providersActivity(): Activity {
        return activity
    }
}