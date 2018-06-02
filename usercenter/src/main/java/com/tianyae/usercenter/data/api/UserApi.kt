package com.tianyae.usercenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.usercenter.data.protocol.RegisterRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req:RegisterRequest): Observable<BaseResponse<String>>
}