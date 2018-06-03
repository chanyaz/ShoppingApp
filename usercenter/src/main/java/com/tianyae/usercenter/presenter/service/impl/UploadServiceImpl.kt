package com.tianyae.usercenter.presenter.service.impl

import com.tianyae.baselibrary.ext.convert
import com.tianyae.usercenter.data.respository.UploadRepository
import com.tianyae.usercenter.presenter.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor(): UploadService {


    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {

        return repository.getUploadToken().convert()
    }
}
