package com.tianyae.usercenter.data.respository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.usercenter.data.api.UploadApi
import com.tianyae.usercenter.data.api.UserApi
import com.tianyae.usercenter.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

class UploadRepository @Inject constructor() {
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }


}