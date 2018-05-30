package com.tianyae.usercenter.presenter.service.impl

import com.tianyae.usercenter.presenter.service.UserService
import io.reactivex.Observable

class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCOde: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}