package com.tianyae.usercenter.presenter.service.impl

import com.tianyae.baselibrary.ext.convert
import com.tianyae.baselibrary.rx.BaseFunc
import com.tianyae.baselibrary.rx.BaseFuncBoolean
import com.tianyae.baselibrary.rx.BaseFuncString
import com.tianyae.usercenter.data.respository.UserRepository
import com.tianyae.usercenter.presenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<String> {

        return repository.register(mobile, verifyCode, pwd)
                .convert()

    }
}
