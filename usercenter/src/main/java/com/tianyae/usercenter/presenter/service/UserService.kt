package com.tianyae.usercenter.presenter.service

import com.tianyae.usercenter.data.protocol.UserInfo
import io.reactivex.Observable

interface UserService {

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<String>

    fun login(mobile: String, pushId: String, pwd: String): Observable<String>

    fun forgetPwd(mobile: String, verifyCode: String): Observable<String>

    fun resetPwd(mobile: String, pwd: String): Observable<String>

}