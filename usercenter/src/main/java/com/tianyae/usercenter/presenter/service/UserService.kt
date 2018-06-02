package com.tianyae.usercenter.presenter.service

import io.reactivex.Observable

interface UserService {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<String>
}