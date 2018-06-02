package com.tianyae.usercenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface UserComponent {
    fun inject(activity:RegisterActivity)
}