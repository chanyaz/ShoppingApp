package com.tianyae.usercenter.injection.module

import com.tianyae.usercenter.presenter.service.UserService
import com.tianyae.usercenter.presenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserModule {

    @Provides
    fun provideUserService(userService: UserServiceImpl): UserService {
        return userService
    }


}
