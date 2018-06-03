package com.tianyae.usercenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResponse<String>>
}
