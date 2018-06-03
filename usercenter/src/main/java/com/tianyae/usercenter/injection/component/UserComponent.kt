package com.tianyae.usercenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.usercenter.injection.module.UploadModule
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.ui.activity.*
import dagger.Component

@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class, UploadModule::class])
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}