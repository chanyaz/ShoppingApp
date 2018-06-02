package com.tianyae.usercenter.presenter.service.impl

import com.tianyae.baselibrary.ext.convertString
import com.tianyae.usercenter.data.respository.UserRepository
import com.tianyae.usercenter.presenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {


    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<String> {

        return repository.register(mobile, verifyCode, pwd)
                .convertString()

    }

    override fun login(mobile: String, pushId: String, pwd: String): Observable<String> {
        return repository.login(mobile, pwd, pushId)
                .convertString()

    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<String> {
        return repository.forgetPwd(mobile, verifyCode)
                .convertString()

    }

    override fun resetPwd(mobile: String, pwd: String): Observable<String> {
        return repository.resetPwd(mobile, pwd)
                .convertString()

    }

}
