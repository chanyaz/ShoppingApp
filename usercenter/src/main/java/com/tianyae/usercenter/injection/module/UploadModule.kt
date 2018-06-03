package com.tianyae.usercenter.injection.module

import com.tianyae.usercenter.presenter.service.UploadService
import com.tianyae.usercenter.presenter.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UploadModule {

    @Provides
    fun provideUserService(uploadServiceImpl: UploadServiceImpl): UploadService {
        return uploadServiceImpl
    }

}
