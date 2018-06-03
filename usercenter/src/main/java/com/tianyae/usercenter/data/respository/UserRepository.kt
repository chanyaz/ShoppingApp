package com.tianyae.usercenter.data.respository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.usercenter.data.api.UserApi
import com.tianyae.usercenter.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterRequest(mobile, pwd, verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginRequest(mobile, pwd, pushId))
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdRequest(mobile, verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdRequest(mobile, pwd))
    }

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserRequest(userIcon, userName, userGender, userSign))
    }
}