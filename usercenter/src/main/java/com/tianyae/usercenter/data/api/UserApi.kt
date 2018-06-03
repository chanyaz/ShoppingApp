package com.tianyae.usercenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.usercenter.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req:RegisterRequest): Observable<BaseResponse<String>>

    @POST("userCenter/login")
    fun login(@Body req:LoginRequest): Observable<BaseResponse<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdRequest): Observable<BaseResponse<UserInfo>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdRequest):Observable<BaseResponse<UserInfo>>

    @POST("userCenter/editUser")
    fun editUser(@Body req:EditUserRequest):Observable<BaseResponse<UserInfo>>

}