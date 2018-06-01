package com.tianyae.usercenter.data.respository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.usercenter.data.api.UserApi
import com.tianyae.usercenter.data.protocol.RegisterRequest
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterRequest(mobile, pwd, verifyCode))
    }
}